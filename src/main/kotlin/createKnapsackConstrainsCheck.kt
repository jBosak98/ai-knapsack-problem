import model.Knapsack

fun createKnapsackConstrainsCheck(knapsackSize:Int, knapsackCapacity:Int): (knapsack:Knapsack) -> Boolean {
   return fun(knapsack: Knapsack):Boolean {
        val isEnoughSpace = knapsack.items.sumBy { it.size } < knapsackSize
        val isLightEnough = knapsack.items.sumBy { it.weight } < knapsackCapacity
        return isEnoughSpace && isLightEnough
    }
}