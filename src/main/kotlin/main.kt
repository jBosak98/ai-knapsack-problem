import generators.*


const val iterations = 1000
const val populationSize = 1000u

const val crossoverProbability = 0.5
const val mutationProbability = 0.5

const val tournamentSize = 5

const val taskFilename = "task"


@ExperimentalUnsignedTypes
fun main() {
    val task = createTaskFromFile("task")
    val (_, knapsackCapacity, knapsackSize, items) = task

    val fulfilment = createKnapsackConstrainsCheck(knapsackSize.toInt(), knapsackCapacity.toInt())
    val creationAlgorithm = getRandomAlgorithm(fulfilment, task)
    val evaluationFunction = generateEvaluate(fulfilment)
    val createIndividual = createIndividualFactory(evaluationFunction)


    val crossover = generateCrossover(crossoverProbability, createIndividual)
    val mutation = generateMutation(mutationProbability, createIndividual, items)
    val selection = generateTournament(tournamentSize)

    val epoch = generateEpoch(crossover, mutation, selection)
    val population = initPopulation(populationSize, creationAlgorithm, createIndividual)
    val finalPopulation = (0..iterations).toList().fold(population, epoch)
}

