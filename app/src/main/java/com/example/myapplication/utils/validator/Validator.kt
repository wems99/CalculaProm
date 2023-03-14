package com.example.myapplication.utils.validator

fun isInRange(minimum: Int, maximum: Int, value: Int): Boolean {
    if (value < minimum || value > maximum) {
        return false;
    }

    return true;
}

fun isStringNotEmpty(value: String?): Boolean {
    return !value.isNullOrEmpty();
}

fun areStringsNotEmpty(vararg values: String?): Boolean {
    for (value in values) {
        if (!isStringNotEmpty(value)) {
            return false;
        }
    }

    return true;
}