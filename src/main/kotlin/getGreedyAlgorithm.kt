import generators.getTheBest
import model.Item
import model.Task

private fun fillKnapsack(
    knapsack: List<Item>,
    allItems: List<Item>,
    fulfilment:(List<Item>) -> Boolean,
    selectorGenerator: (List<Item>) -> (Item) -> Int
): List<Item> {

    if (allItems.isEmpty()) return knapsack
    val selector = selectorGenerator(knapsack)
    val optimalItem = allItems.getTheBest(selector)
    val newItems = knapsack + optimalItem
    val droppedArray = allItems.filter {it.id != optimalItem.id }
    return when (fulfilment(newItems)) {
        true -> fillKnapsack(newItems, droppedArray, fulfilment, selectorGenerator)
        else -> fillKnapsack(knapsack, droppedArray, fulfilment, selectorGenerator)
    }
}

fun getGreedyAlgorithm(
    fulfilment: (List<Item>) -> Boolean,
    task: Task
): (Int) -> List<Item> {
    val (_,  maxWeigth, maxSize,wholeListOfItems) = task
    val createSelectorWithMaxValues = fun(
        maxWeight:Int,
        maxSize:Int
    ):(List<Item>) -> (item:Item) -> Int {

        return fun(items: List<Item>): (item: Item) -> Int {
            val weightLeft = maxWeight - items.sumBy { it.weight }.toDouble()
            val sizeLeft = maxSize - items.sumBy { it.size }.toDouble()

            return fun(item:Item):Int {
                val weightScale = sizeLeft / weightLeft
                val sizeScale = weightLeft / sizeLeft
                val weightScaled = weightScale * item.weight
                val sizeScaled = sizeScale * item.size
                return (item.price / (weightScaled + sizeScaled)).toInt()
            }
        }
    }
    val selectorGenerator = createSelectorWithMaxValues(maxWeigth.toInt(), maxSize.toInt())

    return fun(_: Int): List<Item> { return fillKnapsack(emptyList(), wholeListOfItems,fulfilment, selectorGenerator) }
}