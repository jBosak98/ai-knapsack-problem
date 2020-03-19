import model.Item
import model.Knapsack


fun generateEvaluate(fulfilment:(Knapsack) -> Boolean): (Knapsack) -> Int {
    val evaluate: (Knapsack) -> Int = { knapsack:Knapsack ->
        when {
            (fulfilment(knapsack)) -> knapsack.items.sumBy { it.price }
            else -> 0
        }
    }
    return evaluate
}