import java.io.File
import kotlin.UInt

@ExperimentalUnsignedTypes
fun main() {
    val itemsAmount = 100u
    val knapsackCapacity = 100u
    val knapsackSize = 100u

    val items = generate(itemsAmount, knapsackCapacity, knapsackSize, "xd")
    writeToFile(items, "data")
    val task =
        Task(itemsAmount, knapsackCapacity, knapsackSize, items.toTypedArray())
    val fulfilment = createFulfilment(knapsackSize.toInt(), knapsackCapacity.toInt())
    val randomAlgorithm = getRandomItems(fulfilment, items.toTypedArray(), knapsackSize.toInt(),
        knapsackCapacity.toInt()
    )

    val population = initPopulation(10u, randomAlgorithm)
    val evaluationFunction = generateEvaluate(fulfilment)
    val individuals = population.map{
        Individual(it, evaluationFunction(it))
    }

}
data class Individual(val knapsack:Array<Item>, val evaluation:Int)

@ExperimentalUnsignedTypes
fun generate(n: UInt, w: UInt, s: UInt, output_file: String): List<Item> =
    generateItems(arraySize = n, knapsackCapacity = w, knapsackSize = s)


fun writeToFile(items: List<Item>, outputFile: String) {
    File(outputFile).writeText("XD")
}



