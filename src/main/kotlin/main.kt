import kotlin.UInt

@ExperimentalUnsignedTypes
fun main() {

    println(generate(100u, 100, 100, "xd"))
}


@ExperimentalUnsignedTypes
fun generate(n: UInt, w: Int, s: Int, output_file: String) = generateItems(n, w, s, output_file)




