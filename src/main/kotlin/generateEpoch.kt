import model.Population

fun generateEpoch(
    crossover: (Population) -> Population,
    mutation: (Population) -> Population,
    selection: (Population) -> Population
): (Population, Int) -> Population {
    return { acc: Population, _: Int -> acc.let(crossover).let(mutation).let(selection) }
}