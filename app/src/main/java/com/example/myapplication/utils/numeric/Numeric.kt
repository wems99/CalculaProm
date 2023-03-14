package com.example.myapplication.utils.numeric

import kotlin.math.min

fun calculateAverage(vararg numbers: Double): Double {
    var sum = 0.0


    for (number in numbers) {
        sum += number
    }

    return sum / numbers.size
}

fun toDouble(value: String): Double {
    return value.toDouble()
}

fun isNumeric(value: String): Boolean {
    return value.toDoubleOrNull() != null
}

fun isNumberInRange(minimum: Int, maximum: Int, value: Double): Boolean {
    return value >= minimum && value <= maximum
}

fun areNumbersInRange(minimum: Int, maximum: Int, vararg numbers: Double): Boolean {
    for (number in numbers) {
        if (!isNumberInRange(minimum, maximum, number)) {
            return false
        }
    }

    return true
}