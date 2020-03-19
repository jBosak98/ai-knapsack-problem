package model

@ExperimentalUnsignedTypes
data class ArrayRequirements(
    val size: UInt,
    val minValue: Int,
    val maxValue: Int,
    val fulfillment: (elements: Array<Int>) -> Boolean
)