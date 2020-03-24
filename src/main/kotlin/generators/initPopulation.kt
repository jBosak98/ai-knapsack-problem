package generators

import kotlinx.coroutines.runBlocking
import model.Individual
import model.Item
import model.Population
import utils.amap


@ExperimentalUnsignedTypes
suspend fun initPopulation(
    populationSize: UInt,
    algorithm: (Int) -> List<Item>,
    createIndividual: (items: List<Item>) -> Individual
): Population = runBlocking {
    (0 until populationSize.toInt())
        .amap{algorithm(it)}
        .amap{createIndividual(it) }
        .let {
            Population(it)
        }


}
