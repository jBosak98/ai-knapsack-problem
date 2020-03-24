package generators


import kotlinx.coroutines.runBlocking
import model.Individual
import model.Item
import model.Population
import utils.amap
import kotlin.random.Random
import kotlin.random.nextInt

fun generateCrossover(
    probability: Double,
    createIndividual: ((List<Item>) -> Individual)
): (Population) -> Population {
    fun doCrossover(pair:Pair<Individual,Individual>): Boolean {
        if(pair.first.items.isEmpty() || pair.first.items.isEmpty()) return false
        return Random.nextFloat() < probability
    }
    fun crossover(pair:Pair<Individual,Individual>):Individual {
        val parent1 = pair.first.items
        val parent2 = pair.second.items
        val xPlace = Random.nextInt(parent1.indices)

        val p1Chromosome = parent1.take(xPlace)
        val p2Chromosome = parent2.drop(xPlace)

        return (p1Chromosome + p2Chromosome)
            .distinctBy { it.id }
            .let(createIndividual)

    }
    return fun(population: Population):Population = runBlocking {
        val parents1 = population.individuals.shuffled()
        val parents2 = population.individuals.shuffled()

        val knapsacks = parents1.zip(parents2).amap { pair: Pair<Individual, Individual> ->
            when (doCrossover(pair)) {
                true -> crossover(pair)
                false -> pair.first
            }
        }
        Population(knapsacks)
    }
}