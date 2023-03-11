package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //btnCal use a listener to ejecute the function realizaPromedio()
        val btnCalcular:Button= findViewById(R.id.button)
        btnCalcular.setOnClickListener {
            realizaPromedio()
        }

    }

    private fun realizaPromedio() {
        TODO("Not yet implemented")
    }
}