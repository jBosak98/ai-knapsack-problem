import kotlin.UInt

@ExperimentalUnsignedTypes
fun main() {

    println(generate(100u, 100, 100, "xd"))
}


@ExperimentalUnsignedTypes
fun generate(n: UInt, w: Int, s: Int, output_file: String) {
    val items = generateItems(n, w, s)
    writeToFile(items, output_file)
}

fun writeToFile(items: List<Item>, outputFile: String) {
    TODO("Not yet implemented")
}



