package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

import com.example.myapplication.utils.validator.areStringsNotEmpty
import com.example.myapplication.utils.numeric.calculateAverage
import com.example.myapplication.utils.numeric.toDouble

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
        numbersFocusListener1()
        numbersFocusListener2()
        numbersFocusListener3()

        initElements()
        initListeners()
    }

    private fun numbersFocusListener1() {
        binding.gradeOne.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.gradeOneContainer.helperText = validNUmber1()
            }
        }
    }

    private fun validNUmber1(): String? {
        val numberText = binding.gradeOne.text.toString()
        if (!numberText.matches(".*[0-9].*".toRegex())){
            return "Must be all digits"
        }
        if (numberText.length != 1){
            return "must be 1 digits between 0-9"
        }
        return null
    }

    private fun numbersFocusListener2() {
        binding.gradeTwo.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.gradeTwoContainer.helperText = validNUmber2()
            }
        }
    }

    private fun validNUmber2(): String? {
        val numberText = binding.gradeTwo.text.toString()
        if (!numberText.matches(".*[0-9].*".toRegex())){
            return "Must be all digits"
        }
        if (numberText.length != 1){
            return "must be 1 digits between 0-9"
        }
        return null
    }

    private fun numbersFocusListener3() {
        binding.gradeThree.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.gradeThreeContainer.helperText = validNUmber3()
            }
        }
    }

    private fun validNUmber3(): String? {
        val numberText = binding.gradeThree.text.toString()
        if (!numberText.matches(".*[0-9].*".toRegex())){
            return "Must be all digits"
        }
        if (numberText.length != 1){ //preguntar al profe
            return "must be 1 digits between 0-9"
        }
        return null
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
    }

    private fun onCalculateButtonClick(view: View) {
        val gradeOneText = gradeOne.text.toString()
        val gradeTwoText = gradeTwo.text.toString()
        val gradeThreeText = gradeThree.text.toString()
        val nameText = name.text.toString()


        if (!areStringsNotEmpty(gradeOneText, gradeTwoText, gradeThreeText, nameText)) {
           // showEmptyFieldError(view)
            return
        }

        val gradeOneNumeric = toDouble(gradeOneText)
        val gradeTwoNumeric = toDouble(gradeTwoText)
        val gradeThreeNumeric = toDouble(gradeThreeText)

        val average = calculateAverage(gradeOneNumeric, gradeTwoNumeric, gradeThreeNumeric)

        showGradesAverageResult(average)
    }

    private fun showGradesAverageResult(average: Double) {
        val nameText = name.text.toString()
        val resultStringBuilder = StringBuilder()
        resultStringBuilder.append("El resultado es: ")
        resultStringBuilder.append(average)
        result.text = resultStringBuilder.toString()
        if (average >= 6.66){
            Toast.makeText(this, "Congratz you pass the course " , Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Feels bad men, good luck next time " , Toast.LENGTH_SHORT).show()
        }
    }
}