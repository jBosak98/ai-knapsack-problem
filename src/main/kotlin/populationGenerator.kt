//fun init_population(n_items: UInt, size: UInt) = initPopulation(n_items, size)


@ExperimentalUnsignedTypes
fun initPopulation(populationSize: UInt, algorithm: (Int) -> Array<Item>): List<Array<Item>> {
    return (0..populationSize.toInt()).map(algorithm)
}



val createFulfilment:(knapsackSize:Int, knapsackCapacity:Int) -> (items:Array<Item>) -> Boolean =  {knapsackSize, knapsackCapacity ->
     { items ->
        val isEnoughSpace = items.sumBy { it.size } < knapsackSize
        val isLightEnough = items.sumBy { it.weight } < knapsackCapacity
        isEnoughSpace && isLightEnough
    }
}