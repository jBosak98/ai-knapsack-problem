import model.Item
import model.Task
import kotlin.random.Random


private fun fillKnapsack(fulfilment: (List<Item>) -> Boolean): (items: List<Item>, item: Item) -> List<Item> {
    return fun(items: List<Item>, item: Item): List<Item> {
        val newItems = items + item
        return when (fulfilment(newItems)) {
            true -> newItems
            else -> items
        }
    }
}
fun getRandomAlgorithm(fulfilment: (List<Item>) -> Boolean, task: Task): (Int) -> List<Item> {

    return fun(_: Int): List<Item> {
        val itemsQuantity = Random.nextInt(0, task.items.size)
        return task
            .items
            .shuffled()
            .take(itemsQuantity)
            .fold(emptyList(), fillKnapsack(
//                fulfilment)
                fun(_:List<Item>) = true)
            )
    }

}
