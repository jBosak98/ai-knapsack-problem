import generators.*


const val iterations = 5000
const val populationSize = 100u

const val crossoverProbability = 0.5
const val mutationProbability = 0.003

const val tournamentSize = 22

const val taskFilename = "task"


@ExperimentalUnsignedTypes
fun main() {
    val task = createTaskFromFile(taskFilename)
    val (generatedItemsQuantity, knapsackCapacity, knapsackSize, items) = task

    val fulfilment = createKnapsackConstrainsCheck(knapsackSize.toInt(), knapsackCapacity.toInt())
    val creationAlgorithm = getRandomAlgorithm(fulfilment, task)
    val evaluationFunction = generateEvaluate(fulfilment)
    val createIndividual = createIndividualFactory(evaluationFunction)


    val crossover = generateCrossover(crossoverProbability, createIndividual)
    val mutation = generateMutation(mutationProbability, createIndividual, items)
    val selection = generatePopulationTournament(tournamentSize)

    val epoch = generateEpoch(crossover, mutation, selection)
    val population = initPopulation(populationSize, creationAlgorithm, createIndividual)
    val finalPopulation = (0..iterations).toList().fold(population, epoch)


}

