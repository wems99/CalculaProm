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
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initElements()
        initListeners()
    }

    private fun initElements() {
        calculateButton = findViewById(R.id.calculateButton)
        gradeOne = findViewById(R.id.gradeOne)
        gradeTwo = findViewById(R.id.gradeTwo)
        gradeThree = findViewById(R.id.gradeThree)
        name = findViewById(R.id.name)
        result = findViewById(R.id.result)
    }

    private fun initListeners() {
        calculateButton.setOnClickListener(::onCalculateButtonClick)
        onGradeOneFocus()
        onGradeTwoFocus()
        onGradeThreeFocus()
    }

    private fun onGradeOneFocus() {
        val gradeOneElement = binding.gradeOne

        gradeOneElement.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.gradeOneContainer.helperText = validateGradeNumber("one", gradeOneElement)
            }
        }
    }

    private fun onGradeTwoFocus() {
        val gradeTwoElement = binding.gradeOne

        gradeTwoElement.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.gradeTwoContainer.helperText = validateGradeNumber("two", gradeTwoElement)
            }
        }
    }

    private fun onGradeThreeFocus() {
        val gradeTwoElement = binding.gradeOne

        gradeTwoElement.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.gradeThreeContainer.helperText = validateGradeNumber("three", gradeTwoElement)
            }
        }
    }

    private fun validateGradeNumber(grade: String, gradeElement: EditText): String? {
        val gradeText = gradeElement.text.toString()

        if (!isStringNotEmpty(gradeText)) {
            return "Grade $grade missing"
        }

        if (!isNumeric(gradeText)) {
            return "Grade $grade must be a number"
        }

        val gradeNumber = toDouble(gradeText)

        if (!isNumberInRange(0, 1, gradeNumber)) {
            return "Grade $grade must be between 0 and 1"
        }

        return null
    }

    private fun onCalculateButtonClick(view: View) {
        val gradeOneText = gradeOne.text.toString()
        val gradeTwoText = gradeTwo.text.toString()
        val gradeThreeText = gradeThree.text.toString()
        val nameText = name.text.toString()


        if (!areStringsNotEmpty(gradeOneText, gradeTwoText, gradeThreeText, nameText)) {
           // showEmptyFieldsError(view)
            return
        }

        val gradeOneNumeric = toDouble(gradeOneText)
        val gradeTwoNumeric = toDouble(gradeTwoText)
        val gradeThreeNumeric = toDouble(gradeThreeText)

        if (!areNumbersInRange(0, 1, gradeOneNumeric, gradeThreeNumeric, gradeTwoNumeric)) {
            // showGradesNotInRangeError(view)
            return
        }

        val average = calculateAverage(gradeOneNumeric, gradeTwoNumeric, gradeThreeNumeric)

        showGradesAverageResult(average, nameText)
    }

    private fun showGradesAverageResult(average: Double, nameText: String) {
        val resultStringBuilder = StringBuilder()
        resultStringBuilder.append("The result is: ")
        resultStringBuilder.append(average)
        result.text = resultStringBuilder.toString()
        showGradesAverageResultToast(average, nameText)
    }

    private fun showGradesAverageResultToast(average: Double, nameText: String) {
        if (average >= 6.66){
            Toast.makeText(this, "Congratulations $nameText! You pass the course" , Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Feels bad men. Good luck next time!" , Toast.LENGTH_SHORT).show()
        return
    }
}