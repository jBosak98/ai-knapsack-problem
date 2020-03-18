const val minWeight = 1;
fun maxWeight(knapsackCapacity: Int, objectsSize: Int) = 10 * knapsackCapacity / objectsSize

const val minSize = 1;
fun maxSize(knapsackSize: Int, objectsSize: Int) = 10 * knapsackSize / objectsSize

const val minPrice = 1;
fun maxPrice(objectsSize: Int) = objectsSize

val isProperArray: (minValue: Int) -> ((elements: Array<Int>) -> Boolean) = { minValue ->
    val compare: (elements: Array<Int>) -> Boolean = { elements -> elements.sum() > minValue }
    compare
}

fun isProperWeightsArray(knapsackCapacity: Int) = isProperArray(knapsackCapacity * 2)
fun isProperSizeArray(knapsackSize: Int) = isProperArray(knapsackSize * 2)

@ExperimentalUnsignedTypes
fun generateItems(arraySize: UInt, knapsackCapacity: Int, knapsackSize: Int): List<Item> {

    val weightArrayRequirements =
        ArrayRequirements(arraySize, minWeight, maxWeight(knapsackCapacity, arraySize.toInt()), isProperWeightsArray(knapsackCapacity))
    val weights = getRandomArray(weightArrayRequirements)

    val sizeArrayRequirements =
        ArrayRequirements(arraySize, minSize, maxSize(knapsackSize, arraySize.toInt()), isProperSizeArray(knapsackSize))
    val sizes = getRandomArray(sizeArrayRequirements)

    val priceArrayRequirements = ArrayRequirements(arraySize, minPrice, maxPrice(arraySize.toInt()), fun(_: Array<Int>) = true)
    val prices = getRandomArray(priceArrayRequirements)

    return (0 until arraySize.toInt()).map { index ->
        Item(
            weight = weights[index],
            size = sizes[index],
            price = prices[index]
        )
    }
}