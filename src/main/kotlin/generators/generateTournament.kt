package generators

import model.Population


fun generateTournament(tournamentSize: Int): (Population) -> Population {

    fun tournament(population: Population): Population {
        val individuals = population.individuals.indices.map {
            population.individuals.shuffled().take(tournamentSize).reduce { acc, individual ->
                when (individual.evaluation > acc.evaluation) {
                    true -> individual
                    false -> acc
                }
            }
        }
        return Population(individuals)
    }
    return { population:Population -> tournament(population) }
}
