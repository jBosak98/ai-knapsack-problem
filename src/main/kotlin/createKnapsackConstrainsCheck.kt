import model.Item

fun createKnapsackConstrainsCheck(knapsackSize:Int, knapsackCapacity:Int): (items:Array<Item>) -> Boolean {
   return fun(items: Array<Item>):Boolean {
        val isEnoughSpace = items.sumBy { it.size } < knapsackSize
        val isLightEnough = items.sumBy { it.weight } < knapsackCapacity
        return isEnoughSpace && isLightEnough
    }
}