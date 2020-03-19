import model.Item


fun getRandomAlgorithm(fulfilment: (Array<Item>) -> Boolean, items: Array<Item>, knapsackSize: Int, knapsackCapacity: Int): (Int) -> Array<Item> {
    fun fillKnapsack(fulfilment: (Array<Item>) -> Boolean): (items: Array<Item>, item: Item) -> Array<Item> {
        return { items, item ->
            val newItems = (arrayOf(*items, item))
            when (fulfilment(newItems)) {
                true -> newItems
                false -> items
            }
        }
    }

    val knapsack = items
        .toList()
        .shuffled()
        .fold(emptyArray(), fillKnapsack(fulfilment))
    return { knapsack }
}