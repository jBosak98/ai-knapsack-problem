//fun init_population(n_items: UInt, size: UInt) = initPopulation(n_items, size)


@ExperimentalUnsignedTypes
fun initPopulation(task: Task, populationSize: UInt): List<Array<Item>> {
    val (_, knapsackCapcity, knapsackSize, items: Array<Item>) = task

    val fillKnapsack: (Array<Item>, Item) -> Array<Item> = { knapsack: Array<Item>, item: Item ->
        val isEnoughSpace = knapsack.sumBy { it.size } + item.size < knapsackSize.toInt()
        val isLightEnough = knapsack.sumBy { it.weight } + item.weight < knapsackCapcity.toInt()

        when (isEnoughSpace && isLightEnough) {
            true -> arrayOf(*knapsack, item)
            false -> knapsack
        }
    }
    val getAsMuchItemsAsPossible: (Int) -> Array<Item> = {
        items
            .toList()
            .shuffled()
            .fold(emptyArray(), fillKnapsack)
    }
    return (0..populationSize.toInt()).map(getAsMuchItemsAsPossible)
}