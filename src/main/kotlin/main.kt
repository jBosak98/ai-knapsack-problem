import model.Individual
import model.Item
import model.Task
import java.io.File
import kotlin.UInt

@ExperimentalUnsignedTypes
fun main() {
    val itemsAmount = 100u
    val knapsackCapacity = 100u
    val knapsackSize = 100u

    val crossoverProbability = 0.5
    val mutationProbability = 0.5

    val items = generate(itemsAmount, knapsackCapacity, knapsackSize, "xd")
    writeToFile(items, "data")
    val task =
        Task(itemsAmount, knapsackCapacity, knapsackSize, items.toTypedArray())
    val fulfilment = createKnapsackConstrainsCheck(knapsackSize.toInt(), knapsackCapacity.toInt())
    val randomAlgorithm = getRandomAlgorithm(fulfilment, items.toTypedArray(), knapsackSize.toInt(),
        knapsackCapacity.toInt()
    )
    val evaluationFunction = generateEvaluate(fulfilment)
    val createIndividual = createIndividualFactory(evaluationFunction)

    val population = initPopulation(10u, randomAlgorithm, createIndividual)
    
    val crossover = generateCrossover(crossoverProbability, createIndividual)
    val crossedPopulation = crossover(population)
    val mutation = generateMutation(mutationProbability, createIndividual)



}

@ExperimentalUnsignedTypes
fun generate(n: UInt, w: UInt, s: UInt, output_file: String): List<Item> =
    generateItems(arraySize = n, knapsackCapacity = w, knapsackSize = s)

fun writeToFile(items: List<Item>, outputFile: String) {
    File(outputFile).writeText("XD")
}

