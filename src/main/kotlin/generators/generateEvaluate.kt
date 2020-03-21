package generators

import model.Item


fun generateEvaluate(fulfilment:(List<Item>) -> Boolean): (List<Item>) -> Int {
    val evaluate: (List<Item>) -> Int = { knapsack:List<Item> ->
        when {
            (fulfilment(knapsack)) -> knapsack.sumBy { it.price }
            else -> 0
        }
    }
    return evaluate
}