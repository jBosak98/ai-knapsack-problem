import model.ArrayRequirements
import model.Item

private const val minWeight = 1;
private fun maxWeight(knapsackCapacity: UInt, objectsSize: UInt) = (10u * knapsackCapacity / objectsSize).toUInt()

private const val minSize = 1;
private fun maxSize(knapsackSize: UInt, objectsSize: UInt) = 10u * knapsackSize / objectsSize

private const val minPrice = 1;
private fun maxPrice(objectsSize: Int) = objectsSize

private val isProperArray: (minValue: UInt) -> ((elements: Array<Int>) -> Boolean) = { minValue ->
    val compare: (elements: Array<Int>) -> Boolean = { elements -> elements.sum() > minValue.toInt() }
    compare
}

private fun isProperWeightsArray(knapsackCapacity: UInt) = isProperArray(knapsackCapacity * 2u)
private fun isProperSizeArray(knapsackSize: UInt) = isProperArray(knapsackSize * 2u)

@ExperimentalUnsignedTypes
fun generateItems(arraySize: UInt, knapsackCapacity: UInt, knapsackSize: UInt): List<Item> {

    val weightArrayRequirements =
        ArrayRequirements(
            arraySize,
            minWeight,
            maxWeight(knapsackCapacity, arraySize).toInt(),
            isProperWeightsArray(knapsackCapacity)
        )
    val weights = getRandomArray(weightArrayRequirements)

    val sizeArrayRequirements =
        ArrayRequirements(arraySize, minSize, maxSize(knapsackSize, arraySize).toInt(), isProperSizeArray(knapsackSize))
    val sizes = getRandomArray(sizeArrayRequirements)

    val priceArrayRequirements =
        ArrayRequirements(arraySize, minPrice, maxPrice(arraySize.toInt()), fun(_: Array<Int>) = true)
    val prices = getRandomArray(priceArrayRequirements)

    return (0 until arraySize.toInt()).map { index ->
        Item(
            id = index,
            weight = weights[index],
            size = sizes[index],
            price = prices[index]
        )
    }
}