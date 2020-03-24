package generators

import utils.amap
import utils.getTheBest
import model.Individual
import model.Population
import kotlinx.coroutines.runBlocking


 fun <T> generateTournament(tournamentSize: Int, selector: (T) -> Int): (List<T>) -> List<T> {

    suspend fun <P : T> tournament(population: List<P>): List<P> {
        return population.indices.amap {
            population.shuffled().take(tournamentSize).getTheBest(selector)
        }
    }
    return  fun(population: List<T>): List<T> = runBlocking  { tournament(population) }
}

fun generatePopulationTournament(tournamentSize: Int): (Population) -> Population {
    val selector: (Individual) -> Int =
        { ind1: Individual-> ind1.evaluation }

    val tournament: (List<Individual>) -> List<Individual> = generateTournament(tournamentSize, selector)

    return fun(p: Population): Population { return Population(tournament(p.individuals)) }
}


