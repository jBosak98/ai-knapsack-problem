import model.Item
import model.Task


fun getRandomAlgorithm(fulfilment: (List<Item>) -> Boolean, task: Task): (Int) -> List<Item> {

    fun fillKnapsack(fulfilment: (List<Item>) -> Boolean): (items: List<Item>, item: Item) -> List<Item> {
        return fun(items: List<Item>, item: Item): List<Item> {
            val newItems = items + item
            return when (fulfilment(newItems)) {
                true -> newItems
                else -> items
            }
        }
    }

    return fun(_: Int): List<Item> {
        return task
            .items
            .toList()
            .shuffled()
            .fold(emptyList(), fillKnapsack(fulfilment))
    }

}
