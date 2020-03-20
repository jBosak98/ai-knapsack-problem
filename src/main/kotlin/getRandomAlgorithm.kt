import model.Item
import model.Task


fun getRandomAlgorithm(fulfilment: (Array<Item>) -> Boolean, task:Task): (Int) -> Array<Item> {

    fun fillKnapsack(fulfilment: (Array<Item>) -> Boolean): (items: Array<Item>, item: Item) -> Array<Item> {
        return { items, item ->
            val newItems = (arrayOf(*items, item))
            when (fulfilment(newItems)) {
                true -> newItems
                false -> items
            }
        }
    }

    val knapsack = task.items
        .toList()
        .shuffled()
        .fold(emptyArray(), fillKnapsack(fulfilment))
    return { knapsack }
}
