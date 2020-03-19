import model.Item
import model.Knapsack
import model.Population

//fun init_population(n_items: UInt, size: UInt) = initPopulation(n_items, size)


@ExperimentalUnsignedTypes
fun initPopulation(populationSize: UInt, algorithm: (Int) -> Knapsack): Population {
    return Population((0..populationSize.toInt()).map(algorithm))
}



