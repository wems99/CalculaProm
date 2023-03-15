package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.numeric.*

import com.example.myapplication.utils.validator.areStringsNotEmpty
import com.example.myapplication.utils.validator.isStringNotEmpty
import com.example.myapplication.utils.validator.containsDigits

class MainActivity : AppCompatActivity() {
    private lateinit var calculateButton: Button
    private lateinit var name: EditText
    private lateinit var gradeOne: EditText
    private lateinit var gradeTwo: EditText
    private lateinit var gradeThree: EditText
    private lateinit var result: TextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initElements()
        initListeners()
    }

    /**
     * Sets up app elements
     */
    private fun initElements() {
        calculateButton = binding.calculateButton
        gradeOne = binding.gradeOne
        gradeTwo = binding.gradeTwo
        gradeThree = binding.gradeThree
        name = binding.name
        result = binding.result
    }

    /**
     * Sets up app listeners
     */
    private fun initListeners() {
        calculateButton.setOnClickListener(::onCalculateButtonClick)
        onNameFocus()
        onGradeOneFocus()
        onGradeTwoFocus()
        onGradeThreeFocus()
    }

    /**
     * Sets a focus change listener on name field
     * Shows an error if name not defined or empty
     */
    private fun onNameFocus() {
        name.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                val nameText = name.text.toString()

                if (!isStringNotEmpty(nameText)) {
                    binding.nameContainer.helperText = "Name is required"
                } else if (containsDigits(nameText)) {
                    binding.nameContainer.helperText = "Name cannot contain digits"
                }
                else {
                    binding.nameContainer.helperText = null
                }
            }
        }
    }

    /**
     * Sets a focus change listener on grade one
     * Shows an error if grade not defined or empty
     * Shows an error if grade not between 0 and 10
     */
    private fun onGradeOneFocus() {
        gradeOne.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.gradeOneContainer.helperText = validateGradeNumber("one", gradeOne)
            }
        }
    }

    /**
     * Sets a focus change listener on grade two
     * Shows an error if grade not defined or empty
     * Shows an error if grade not between 0 and 10
     */
    private fun onGradeTwoFocus() {
        gradeTwo.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.gradeTwoContainer.helperText = validateGradeNumber("two", gradeTwo)
            }
        }
    }

    /**
     * Sets a focus change listener on grade three
     * Shows an error if grade not defined or empty
     * Shows an error if grade not between 0 and 10
     */
    private fun onGradeThreeFocus() {
        gradeThree.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.gradeThreeContainer.helperText = validateGradeNumber("three", gradeThree)
            }
        }
    }

    /**
     * Validates if the grade contained in `gradeElement`
     * is defined, not empty and between 0 and 10
     * @param grade grade label for the one to validate
     * @param gradeElement app element that contains the grade
     * @returns string message with the error or null if no errors were found
     */
    private fun validateGradeNumber(grade: String, gradeElement: EditText): String? {
        val gradeText = gradeElement.text.toString()

        if (!isStringNotEmpty(gradeText)) {
            return "Grade $grade missing"
        }

        if (!isNumeric(gradeText)) {
            return "Grade $grade must be a number"
        }

        val gradeNumber = toDouble(gradeText)

        if (!isNumberInRange(0, 10, gradeNumber)) {
            return "Grade $grade must be between 0 and 10"
        }

        return null
    }

    /**
     * Handles the click on the calculate button
     * @param view View where event occurs. Not used but required to set the listener
     */
    private fun onCalculateButtonClick(view: View) {
        val gradeOneText = gradeOne.text.toString()
        val gradeTwoText = gradeTwo.text.toString()
        val gradeThreeText = gradeThree.text.toString()
        val nameText = name.text.toString()


        if (!areStringsNotEmpty(gradeOneText, gradeTwoText, gradeThreeText, nameText)) {
            return
        }

        val gradeOneNumeric = toDouble(gradeOneText)
        val gradeTwoNumeric = toDouble(gradeTwoText)
        val gradeThreeNumeric = toDouble(gradeThreeText)

        if (!areNumbersInRange(0, 10, gradeOneNumeric, gradeThreeNumeric, gradeTwoNumeric)) {
            return
        }

        val average = calculateAverage(gradeOneNumeric, gradeTwoNumeric, gradeThreeNumeric)

        showGradesAverageResult(average, nameText)
    }

    /**
     * Shows the average of the grades
     * @param average result of the grades average
     * @param nameText name of the person who owns the grades
     */
    private fun showGradesAverageResult(average: Double, nameText: String) {
        val resultStringBuilder = StringBuilder()
        resultStringBuilder.append("The result is: ")
        resultStringBuilder.append(formatDoubleNumber(average, 2))
        result.text = resultStringBuilder.toString()
        showGradesAverageResultToast(average, nameText)
    }

    /**
     * Shows the resolution of the grades average on a Toast
     * @param average result of the grades average
     * @param nameText name of the person who owns the grades
     */
    private fun showGradesAverageResultToast(average: Double, nameText: String) {
        if (average >= 6.66){
            Toast.makeText(this, "Congratulations $nameText! You pass the course" , Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Feels bad men. Good luck next time!" , Toast.LENGTH_SHORT).show()
        return
    }
}
