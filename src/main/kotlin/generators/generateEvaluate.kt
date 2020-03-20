package generators

import model.Item


fun generateEvaluate(fulfilment:(Array<Item>) -> Boolean): (Array<Item>) -> Int {
    val evaluate: (Array<Item>) -> Int = { knapsack:Array<Item> ->
        when {
            (fulfilment(knapsack)) -> knapsack.sumBy { it.price }
            else -> 0
        }
    }
    return evaluate
}