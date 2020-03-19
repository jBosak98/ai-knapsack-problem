


fun generateEvaluate(fulfilment:(Array<Item>) -> Boolean): (Array<Item>) -> Int {
    val evaluate: (Array<Item>) -> Int = { items:Array<Item> ->
        when {
            (fulfilment(items)) -> items.sumBy { it.price }
            else -> 0
        }
    }
    return evaluate
}