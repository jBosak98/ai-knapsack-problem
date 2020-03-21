import model.Individual
import model.Item

fun createKnapsackConstrainsCheck(knapsackSize:Int, knapsackCapacity:Int): (individual:List<Item>) -> Boolean {
   return fun(items: List<Item>):Boolean {
        val isEnoughSpace = items.sumBy { it.size } < knapsackSize
        val isLightEnough = items.sumBy { it.weight } < knapsackCapacity
        return isEnoughSpace && isLightEnough
    }
}