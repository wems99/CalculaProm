package com.example.myapplication.utils.validator

/**
 * Determines if a string is defined and not empty
 * @param value string to be evaluated
 * @return true if string defined and not empty, false if not
 */
fun isStringNotEmpty(value: String?): Boolean {
    return !value.isNullOrEmpty();
}

/**
 * Determines if a list of strings are defined and not empty
 * @param values list of strings to be evaluated
 * @return true if each element on the list is defined and not empty, false if not
 */
fun areStringsNotEmpty(vararg values: String?): Boolean {
    for (value in values) {
        if (!isStringNotEmpty(value)) {
            return false;
        }
    }

    return true;
}

/**
 * Determines if a string contains digits
 * @param value the text to be evaluated
 * @return false if contains digits, true if not
 */
fun containsDigits(value: String): Boolean {
    val regex = Regex("\\d+")
    return regex.containsMatchIn(value)
}
