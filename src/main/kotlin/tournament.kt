import model.Individual
import model.Population

fun tournament(population: Population, tournamentSize:Int): Population {
    val individuals = population.individuals.map {
        population.individuals.shuffled().take(tournamentSize).reduce { acc, individual ->
            when(individual.evaluation > acc.evaluation){
                true ->individual
                else -> acc
            }
        }
    }
    return Population(individuals)

}