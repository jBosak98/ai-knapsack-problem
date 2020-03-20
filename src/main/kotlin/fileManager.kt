import generators.generateItems
import model.Item
import model.Task
import java.io.File
import java.lang.Exception

@ExperimentalUnsignedTypes
private fun writeTaskToFile(task: Task, outputFile: String) {
    val configCSV = getTaskConfigCSV(task)
    val itemsCSV = getTaskItemsCSV(task)
    File(outputFile).writeText(configCSV + itemsCSV)
}

@ExperimentalUnsignedTypes
private fun readTaskFromFile(file:File): Task {
    val lines = file.readLines()
    val (arraySize,  knapsackCapacity, knapsackSize) = lines.first().split(",")
    val items = getItemsFromCSV(lines.drop(1))
    return Task(arraySize.toUInt(),knapsackCapacity.toUInt(), knapsackSize.toUInt(), items)
}

private fun getItemsFromCSV(lines:List<String>) =
    lines.mapIndexed { index, it ->
        val (weight,size,price) = it.split(",")
        Item(index, weight.toInt(), size.toInt(), price.toInt())
    }.toTypedArray()

private fun getTaskConfigCSV(task:Task): String {
    val (arraySize,  knapsackCapacity, knapsackSize,_) = task
    return "$arraySize,$knapsackCapacity,$knapsackSize\n"
}
private fun getTaskItemsCSV(task:Task): String {
    return task.items.sortedBy { it.id }.fold(""){ acc, item ->
        val (_, weight, size, price) = item
        "$acc$weight,$size,$price\n"
    }

}

@ExperimentalUnsignedTypes
fun createTaskFromFile(filename: String): Task {
    val file = File(filename)
    val itemsAmount = 100u
    val knapsackCapacity = 100u
    val knapsackSize = 100u
    try {
        if (file.exists()) return readTaskFromFile(file)
    }catch (e: Exception){}

    val items = generateItems(itemsAmount, knapsackCapacity, knapsackSize)
    val task = Task(itemsAmount, knapsackCapacity, knapsackSize, items)
    writeTaskToFile(task, filename)
    return task
}
