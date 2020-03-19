import model.Item
import model.Task
import java.io.File
import kotlin.UInt

const val itemsAmount = 100u
const val knapsackCapacity = 100u
const val knapsackSize = 100u

const val iterations = 1000
const val populationSize = 1000u

const val crossoverProbability = 0.5
const val mutationProbability = 0.5

const val tournamentSize = 5

const val outputfile = "xd"

@ExperimentalUnsignedTypes
fun main() {
    val items = generate(itemsAmount, knapsackCapacity, knapsackSize, outputfile)
    writeToFile(items, outputfile)
    val task =
        Task(itemsAmount, knapsackCapacity, knapsackSize, items.toTypedArray())
    val fulfilment = createKnapsackConstrainsCheck(knapsackSize.toInt(), knapsackCapacity.toInt())
    val creationAlgorithm = getRandomAlgorithm(fulfilment, items.toTypedArray(), knapsackSize.toInt(),
        knapsackCapacity.toInt()
    )
    val evaluationFunction = generateEvaluate(fulfilment)
    val createIndividual = createIndividualFactory(evaluationFunction)
    val crossover = generateCrossover(crossoverProbability, createIndividual)
    val mutation = generateMutation(mutationProbability, createIndividual, items.toTypedArray())


    val selection = generateTournament(tournamentSize)

    val epoch = generateEpoch(crossover, mutation, selection)


    val population = initPopulation(populationSize, creationAlgorithm, createIndividual)
    val finalPopulation = (0..iterations).toList().fold(population, epoch)
}

@ExperimentalUnsignedTypes
fun generate(n: UInt, w: UInt, s: UInt, output_file: String): List<Item> =
    generateItems(arraySize = n, knapsackCapacity = w, knapsackSize = s)

fun writeToFile(items: List<Item>, outputFile: String) {
    File(outputFile).writeText("XD")
}

