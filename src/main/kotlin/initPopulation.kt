import model.Item

//fun init_population(n_items: UInt, size: UInt) = initPopulation(n_items, size)


@ExperimentalUnsignedTypes
fun initPopulation(populationSize: UInt, algorithm: (Int) -> Array<Item>): List<Array<Item>> {
    return (0..populationSize.toInt()).map(algorithm)
}



