import kotlin.UInt

@ExperimentalUnsignedTypes
fun main() {
    val itemsAmount = 100u
    val knapsackCapacity = 100u
    val knapsackSize = 100u

    val items = generate(itemsAmount, knapsackCapacity, knapsackSize, "xd")
    val task =
        Task(itemsAmount, knapsackCapacity, knapsackSize, items.toTypedArray())
    val population = initPopulation(task, 10u)
}


@ExperimentalUnsignedTypes
fun generate(n: UInt, w: UInt, s: UInt, output_file: String): List<Item> =
    generateItems(arraySize = n, knapsackCapacity = w, knapsackSize = s)


fun writeToFile(items: List<Item>, outputFile: String) {
//    TODO("Not yet implemented")
}



