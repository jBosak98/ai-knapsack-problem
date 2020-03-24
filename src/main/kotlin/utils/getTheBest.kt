package utils

fun <T> List<T>.getTheBest(selector: (T) -> Int): T =
    this.reduce { best, item ->
        when (selector(best) > selector(item)) {
            true -> best
            else -> item
        }
    }
