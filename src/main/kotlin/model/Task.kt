package model

data class Task(
    val arraySize: UInt, //number of generated items
    val knapsackCapacity: UInt, //max carrying capacity
    val knapsackSize: UInt, // max knapsack size
    val items: Array<Item>
)