const val minWeight = 1;
fun maxWeight(knapsackCapacity: UInt, objectsSize: UInt) = (10u * knapsackCapacity / objectsSize).toUInt()

const val minSize = 1;
fun maxSize(knapsackSize: UInt, objectsSize: UInt) = 10u * knapsackSize / objectsSize

const val minPrice = 1;
fun maxPrice(objectsSize: Int) = objectsSize

val isProperArray: (minValue: UInt) -> ((elements: Array<Int>) -> Boolean) = { minValue ->
    val compare: (elements: Array<Int>) -> Boolean = { elements -> elements.sum() > minValue.toInt() }
    compare
}

fun isProperWeightsArray(knapsackCapacity: UInt) = isProperArray(knapsackCapacity * 2u)
fun isProperSizeArray(knapsackSize: UInt) = isProperArray(knapsackSize * 2u)

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