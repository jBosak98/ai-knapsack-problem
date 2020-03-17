import kotlin.random.Random

fun main() {
    println("xd")
    generate(100,100,100,"xd")
}

fun generate(n: Int, w: Int, s: Int, output_file: String) = generatePopulation(n, w, s, output_file)

fun generatePopulation(objectsSize: Int, knapsackCapacity: Int, knapsackSize: Int, output_file: String) {
    val items = arrayOfNulls<Unit>(objectsSize).map {
        val weight = Random.nextInt(1,10 * knapsackCapacity / objectsSize)
        val size = Random.nextInt(1, 10*knapsackSize / objectsSize)
        val price = Random.nextInt(1,objectsSize)
        return@map Item(weight,size,price)
    }
    print(items)



}

data class Item(val weight: Int, val size: Int, val price:Int)
