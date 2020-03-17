import jdk.nashorn.internal.objects.NativeDebug.map


import kotlin.random.Random
import kotlin.UInt

@ExperimentalUnsignedTypes
fun main() {

    generate(100u, 100, 100, "xd")
}


@ExperimentalUnsignedTypes
fun generate(n: UInt, w: Int, s: Int, output_file: String) = generateItems(n, w, s, output_file)


val isProperArray: (minValue: Int) -> ((elements: List<Int>) -> Boolean) = { minValue ->
    val compare: (elements: List<Int>) -> Boolean = { elements -> elements.sum() > minValue }
    compare
}

@ExperimentalUnsignedTypes
fun generateItems(objectsSize: UInt, knapsackCapacity: Int, knapsackSize: Int, output_file: String): List<Item> {

    val isProperWeightsArray = isProperArray(knapsackCapacity * 2)
    val isProperSizeArray = isProperArray(knapsackSize * 2)

    val weights = getRandomArray(objectsSize, 1, 10 * knapsackCapacity / objectsSize.toInt(), isProperWeightsArray)
    val sizes = getRandomArray(objectsSize, 1, 10 * knapsackSize / objectsSize.toInt(), isProperSizeArray)
    val prices  = getRandomArray(objectsSize,1, objectsSize.toInt(), fun(_: List<Int>) = true)

    return (0 until objectsSize.toInt()).map{ Item(weights[it], sizes[it], prices[it]) }

}


@ExperimentalUnsignedTypes
fun getRandomArray(size: UInt, from: Int, to: Int, fulfillment: (elements: List<Int>) -> Boolean): Array<Int> {
    var elements: Array<Int> = emptyArray()
    do {
        elements = arrayOfNulls<Int>(size.toInt()).map { Random.nextInt(from, to) }.toTypedArray()
    } while (!fulfillment(elements.toList()))
    return elements
}


data class Item(val weight: Int, val size: Int, val price: Int)
