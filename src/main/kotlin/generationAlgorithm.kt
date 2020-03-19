

fun getRandomItems(fulfilment: (Array<Item>) -> Boolean, items: Array<Item>, knapsackSize: Int, knapsackCapacity: Int): (Int) -> Array<Item> {
    fun fillKnapsack(fulfilment: (Array<Item>) -> Boolean): (knapsack: Array<Item>, item: Item) -> Array<Item> {
        return { knapsack, item ->
            val newKnapsack = arrayOf(*knapsack, item)
            when (fulfilment(newKnapsack)) {
                true -> newKnapsack
                false -> knapsack
            }
        }
    }

    val knapsack = items
        .toList()
        .shuffled()
        .fold(emptyArray(), fillKnapsack(fulfilment))
    return { knapsack }
}
