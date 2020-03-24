import generators.*
import kotlinx.coroutines.*
import model.Individual
import model.Population
import model.Task
import utils.getTheBest
import java.io.File
import kotlin.math.roundToInt
import kotlin.system.measureTimeMillis


const val iterations = 1000//1000//5000
const val populationSize = 10

const val crossoverProbability = 0.5
const val mutationProbability = 0.01

val tournamentSize = (populationSize.toDouble() * 0.23).roundToInt()

const val taskFilename = "task"


@ExperimentalUnsignedTypes
fun main() = runBlocking {

    val task = createTaskFromFile(taskFilename)
    val (generatedItemsQuantity, knapsackCapacity, knapsackSize, items) = task

    val fulfilment = createKnapsackConstrainsCheck(knapsackSize.toInt(), knapsackCapacity.toInt())
    val creationAlgorithm = getRandomAlgorithm(fulfilment, task)
    val evaluationFunction = generateEvaluate(fulfilment)
    val createIndividual = createIndividualFactory(evaluationFunction)

//    val greedyAlgorithm = getGreedyAlgorithm(fulfilment, task)

    val crossover = generateCrossover(crossoverProbability, createIndividual)
    val mutation = generateMutation(mutationProbability, createIndividual, items)
    val selection = generatePopulationTournament(tournamentSize)

    val epoch = generateEpoch(crossover, mutation, selection)
    val initialPopulation = initPopulation(populationSize.toUInt(), creationAlgorithm, createIndividual)

    val finalPopulation = (0..iterations).fold(initialPopulation,epoch)

}



