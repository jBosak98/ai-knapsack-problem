package generators

import model.Individual
import model.Item
import model.Population
import kotlin.random.Random
import kotlin.random.nextInt

fun generateCrossover(
    probability: Double,
    createIndividual: ((Array<Item>) -> Individual)
): (Population) -> Population {
    val doCrossover = {
        Random.nextFloat() < probability
    }
    val crossover = { pair: Pair<Individual, Individual> ->
        val parent1 = pair.first.items
        val parent2 = pair.second.items

        val xPlace = Random.nextInt(parent1.indices)
        val p1Chromosome = parent1.take(xPlace).toTypedArray()
        val p2Chromosome = parent2.drop(xPlace).toTypedArray()

        val child = arrayOf(*p1Chromosome, *p2Chromosome).distinctBy { it.id }.toTypedArray()
        createIndividual(child)
    }
    return { population: Population ->
        val parents1 = population.individuals.toList().shuffled()
        val parents2 = population.individuals.toList().shuffled()

        val knapsacks = parents1.zip(parents2).map { pair: Pair<Individual, Individual> ->
            when (doCrossover()) {
                true -> crossover(pair)
                false -> pair.first
            }
        }.toTypedArray()
        Population(knapsacks.toList())
    }
}