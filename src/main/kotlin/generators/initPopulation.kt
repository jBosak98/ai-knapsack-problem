package generators

import model.Individual
import model.Item
import model.Population


@ExperimentalUnsignedTypes
fun initPopulation(
    populationSize: UInt,
    algorithm: (Int) -> Array<Item>,
    createIndividual: (items: Array<Item>) -> Individual
): Population =
    (0 until populationSize.toInt())
        .map(algorithm)
        .map(createIndividual)
        .let { Population(it) }





