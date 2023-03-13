package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.os.Bundle
import android.view.View

import com.example.myapplication.utils.validator.areStringsNotEmpty
import com.example.myapplication.utils.numeric.calculateAverage
import com.example.myapplication.utils.numeric.toDouble

class MainActivity : AppCompatActivity() {
    private lateinit var calculateButton: Button;
    private lateinit var name: EditText;
    private lateinit var gradeOne: EditText;
    private lateinit var gradeTwo: EditText;
    private lateinit var gradeThree: EditText;
    private lateinit var result: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initElements();
        initListeners();
    }

    private fun initElements() {
        calculateButton = findViewById(R.id.calculateButton);
        gradeOne = findViewById(R.id.gradeOne);
        gradeTwo = findViewById(R.id.gradeTwo);
        gradeThree = findViewById(R.id.gradeThree);
        name = findViewById(R.id.name);
        result = findViewById(R.id.result);
    }

    private fun initListeners() {
        calculateButton.setOnClickListener(::onCalculateButtonClick);
    }

    private fun onCalculateButtonClick(view: View) {
        val gradeOneText = gradeOne.text.toString();
        val gradeTwoText = gradeTwo.text.toString();
        val gradeThreeText = gradeThree.text.toString();
        val nameText = name.text.toString();

        if (!areStringsNotEmpty(gradeOneText, gradeTwoText, gradeThreeText, nameText)) {
            showEmptyFieldError(view);
            return;
        }

        val gradeOneNumeric = toDouble(gradeOneText);
        val gradeTwoNumeric = toDouble(gradeTwoText);
        val gradeThreeNumeric = toDouble(gradeThreeText);

        val average = calculateAverage(gradeOneNumeric, gradeTwoNumeric, gradeThreeNumeric);

        showGradesAverageResult(average);
    }

    private fun showEmptyFieldError(view: View) {
        // handle empty form field error
    }

    private fun showGradesAverageResult(average: Double) {
        val resultStringBuilder = StringBuilder();
        resultStringBuilder.append("El resultado es: ");
        resultStringBuilder.append(average);
        result.text = resultStringBuilder.toString();
    }
}