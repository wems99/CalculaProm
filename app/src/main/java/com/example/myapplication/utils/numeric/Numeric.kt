package com.example.myapplication.utils.numeric

/**
 * Calculates the average of the given numbers
 * @param numbers list of numbers to calculate the average
 * @return the average of `numbers`
 */
fun calculateAverage(vararg numbers: Double): Double {
    var sum = 0.0

    for (number in numbers) {
        sum += number
    }

    return sum / numbers.size
}

/**
 * Converts a string to a double number
 * @param value number in string format
 * @return the conversion of value to a double
 */
fun toDouble(value: String): Double {
    return value.toDouble()
}

/**
 * Determines if a string is numeric
 * @param value number in string format
 * @return true if the string is a number, false if not
 */
fun isNumeric(value: String): Boolean {
    return value.toDoubleOrNull() != null
}

/**
 * Determines if a number is in a given range
 * @param minimum the minimum value that the number can take
 * @param maximum the maximum value that the number can take
 * @param value the number to be evaluated
 * @return true if the number is in the range, false if not
 */
fun isNumberInRange(minimum: Int, maximum: Int, value: Double): Boolean {
    return value >= minimum && value <= maximum
}

/**
 * Determines if a given list of numbers are in a given range
 * @param minimum the minimum value that the number can take
 * @param maximum the maximum value that the number can take
 * @param numbers list of numbers to be evaluated
 * @return true if each element on the list are in the range, false if not
 */
fun areNumbersInRange(minimum: Int, maximum: Int, vararg numbers: Double): Boolean {
    for (number in numbers) {
        if (!isNumberInRange(minimum, maximum, number)) {
            return false
        }
    }

    return true
}

/**
 * Formats a double number value with a given decimals
 * @param value number to be formatted
 * @param decimals amount of decimals that the value must contain
 * @returns a formatted double number with the given decimals
 */
fun formatDoubleNumber(value: Double, decimals: Int): Double {
    val formattedValue: String = String.format("%.${decimals}f", value)

    return formattedValue.toDouble()
}
