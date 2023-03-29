package com.example.firstapp

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences
    private val keyForNumber = "key_for_number"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("myFile", MODE_PRIVATE)

        val plusButton = findViewById<Button>(R.id.plusButton)
        val minusButton = findViewById<Button>(R.id.minusButton)
        val myText = findViewById<TextView>(R.id.textView)

        var counter = getNumber()
        myText.text = counter.toString()

        plusButton.setOnClickListener {
            Log.d("My Tag", "Click")
            counter += 1
            saveNumber(counter)
            val numberAsString = counter.toString()
            myText.text = numberAsString
        }

        minusButton.setOnClickListener {
            Log.d("My Tag", "Click")
            counter -= 1
            saveNumber(counter)
            val numberAsString = counter.toString()
            myText.text = numberAsString
        }
    }

    private fun saveNumber(number: Int) {

        val editor = sharedPref.edit()
        editor.putInt(keyForNumber, number)
        editor.apply()
    }

    private fun getNumber(): Int {

        return sharedPref.getInt(keyForNumber, 0)
    }
}