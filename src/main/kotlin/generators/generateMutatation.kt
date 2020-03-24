package generators

import kotlinx.coroutines.runBlocking
import model.Individual
import model.Item
import model.Population
import utils.amap

suspend fun generateMutation(
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
    return fun(population: Population):Population = runBlocking {
        Population(population.individuals.amap { individual ->  mutation(individual) })
    }
}