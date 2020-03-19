import model.Individual
import model.Item

fun createIndividualFactory(evaluation:(Array<Item>) -> Int): (Array<Item>) -> Individual {
    val createIndividual:(items: Array<Item>)-> Individual = { items->
        Individual(items, evaluation(items))
    }
    return createIndividual
}
