import model.Individual
import model.Item
import model.Population


@ExperimentalUnsignedTypes
fun initPopulation(
    populationSize: UInt,
    algorithm: (Int) -> Array<Item>,
    createIndividual: (items: Array<Item>) -> Individual
): Population {
    val knapsacks = (0..populationSize.toInt()).map(algorithm)
    return Population(knapsacks.map { knapsack ->
        createIndividual(knapsack)
    })
}



