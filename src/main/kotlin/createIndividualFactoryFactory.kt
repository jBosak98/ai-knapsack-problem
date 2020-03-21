import model.Individual
import model.Item

fun createIndividualFactory(evaluation:(List<Item>) -> Int): (List<Item>) -> Individual {
    val createIndividual:(items: List<Item>)-> Individual = { items->
        Individual(items, evaluation(items))
    }
    return createIndividual
}
