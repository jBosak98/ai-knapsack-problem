package generators

import model.Individual
import model.Item
import model.Population

fun generateMutation(
    probability: Double,
    createIndividual: (List<Item>) -> Individual,
    items: List<Item>
): (Population) -> Population {

    val mutationQuantity = (items.size * probability).toInt()
    val getMutations = { items.shuffled().take(mutationQuantity) }
    val mutation = { individual: Individual ->
        val mutations = getMutations()
        val genome = individual.items
        val postMutation = mutations.filter { mutation -> genome.all{ it.id != mutation.id } }
        val postGenome = genome.filter { gen -> mutations.all{ it.id != gen.id } }
        val coalescence = postGenome + postMutation
        createIndividual(coalescence)
    }
    return { population: Population ->
        Population(population.individuals.map { individual ->  mutation(individual) })
    }
}