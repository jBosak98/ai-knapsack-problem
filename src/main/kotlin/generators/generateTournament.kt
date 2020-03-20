package generators

import model.Individual
import model.Population


fun <T> generateTournament(tournamentSize: Int, comparator: (T, T) -> Boolean): (List<T>) -> List<T> {

    fun <P : T> tournament(population: List<P>): List<P> {
        return population.indices.map {
            population.shuffled().take(tournamentSize).getTheBest(comparator)
        }
    }
    return fun(population: List<T>): List<T> { return tournament(population) }
}

fun generatePopulationTournament(tournamentSize: Int): (Population) -> Population {
    val comparator: (Individual, Individual) -> Boolean =
        { ind1: Individual, ind2: Individual -> ind1.evaluation > ind2.evaluation }

    val tournament: (List<Individual>) -> List<Individual> = generateTournament(tournamentSize, comparator)

    return fun(p: Population): Population { return Population(tournament(p.individuals)) }
}


fun <T> List<T>.getTheBest(comparator: (T, T) -> Boolean): T =
    this.reduce { best, item ->
        when (comparator(best, item)) {
            true -> best
            else -> item
        }
    }
