import model.ArrayRequirements
import kotlin.random.Random

@ExperimentalUnsignedTypes
private fun randomArray(array: List<Int>, requirements: ArrayRequirements): List<Int> {
    val (size, minValue, maxValue, fulfillment) = requirements

    fun getRandomInt(minValue: Int, maxValue: Int) = Random.nextInt(minValue, maxValue)

    return when {
        array.size == size.toInt() && fulfillment(array) -> array
        array.size == size.toInt() -> randomArray(
            array.drop(1),
            requirements
        )
        else -> randomArray(
            array + getRandomInt(minValue, maxValue),
            requirements
        )
    }
}



@ExperimentalUnsignedTypes
fun getRandomArray(requirements: ArrayRequirements): List<Int> {
    return randomArray(emptyList(), requirements)
}

