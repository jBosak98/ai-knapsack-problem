import kotlin.random.Random

@ExperimentalUnsignedTypes
fun randomArray(array: Array<Int>, requirements: ArrayRequirements): Array<Int> {
    val (size, minValue, maxValue, fulfillment) = requirements

    fun getRandomInt(minValue: Int, maxValue: Int) = Random.nextInt(minValue, maxValue)

    return when {
        array.size == size.toInt() && fulfillment(array) -> array
        array.size == size.toInt() -> randomArray(
            array.drop(1).toTypedArray(),
            requirements
        )
        else -> randomArray(
            arrayOf(*array, getRandomInt(minValue, maxValue)),
            requirements
        )
    }
}



@ExperimentalUnsignedTypes
fun getRandomArray(requirements: ArrayRequirements): Array<Int> {
    return randomArray(emptyArray(), requirements)
}

@ExperimentalUnsignedTypes
data class ArrayRequirements(
    val size: UInt,
    val minValue: Int,
    val maxValue: Int,
    val fulfillment: (elements: Array<Int>) -> Boolean
)