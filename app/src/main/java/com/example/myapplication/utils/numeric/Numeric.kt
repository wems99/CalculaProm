package com.example.myapplication.utils.numeric

fun calculateAverage(vararg numbers: Double): Double {
    var sum = 0.0;


    for (number in numbers) {
        sum += number;
    }

    return sum / numbers.size;
}

fun toDouble(value: String): Double {
    return value.toDouble();
}