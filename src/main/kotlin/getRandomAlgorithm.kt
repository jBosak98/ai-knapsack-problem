import model.Item
import model.Knapsack

fun getRandomAlgorithm(fulfilment: (Knapsack) -> Boolean, items: Array<Item>, knapsackSize: Int, knapsackCapacity: Int): (Int) -> Knapsack {
    fun fillKnapsack(fulfilment: (Knapsack) -> Boolean): (knapsack: Knapsack, item: Item) -> Knapsack {
        return { knapsack, item ->
            val newKnapsack = Knapsack(arrayOf(*knapsack.items, item))
            when (fulfilment(newKnapsack)) {
                true -> newKnapsack
                false -> knapsack
            }
        }
    }

    val knapsack = items
        .toList()
        .shuffled()
        .fold(Knapsack(emptyArray()), fillKnapsack(fulfilment))
    return { knapsack }
}
