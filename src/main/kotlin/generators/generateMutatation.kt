package generators

import model.Individual
import model.Item
import model.Population

fun generateMutation(probability: Double, createIndividual: (Array<Item>) -> Individual, items: Array<Item>): (Population) -> Population {
    val mutationQuantity = (items.size * probability).toInt()
    val getMutations = {items.toList().shuffled().take(mutationQuantity).toTypedArray()}
    val mutation = { individual: Individual ->
        val mutations = getMutations()
        val genome = individual.items
        val postMutation = mutations.filter { mutation -> genome.all{it.id != mutation.id} }.toTypedArray()
        val postGenome = genome.filter { gen -> mutations.all{it.id != gen.id} }.toTypedArray()
        val coalescence = arrayOf(*postGenome, *postMutation)
        createIndividual(coalescence)
    }
    return { population: Population ->
        Population(population.individuals.map { individual ->  mutation(individual) })
    }
}