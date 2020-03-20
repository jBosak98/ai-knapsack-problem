import model.Item
import model.Task


fun getRandomAlgorithm(fulfilment: (Array<Item>) -> Boolean, task: Task): (Int) -> Array<Item> {

    fun fillKnapsack(fulfilment: (Array<Item>) -> Boolean): (items: Array<Item>, item: Item) -> Array<Item> {
        return fun(items: Array<Item>, item: Item): Array<Item> {
            val newItems = (arrayOf(*items, item))
            return when (fulfilment(newItems)) {
                true -> newItems
                else -> items
            }
        }
    }

    return fun(_: Int): Array<Item> {
        return task
            .items
            .toList()
            .shuffled()
            .fold(emptyArray(), fillKnapsack(fulfilment))
    }

}
