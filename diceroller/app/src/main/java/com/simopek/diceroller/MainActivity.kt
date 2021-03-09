package com.simopek.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        val countUpButton: Button = findViewById(R.id.count_up_button)
        countUpButton.setOnClickListener { countUp() }
    }

    private fun getRollResultTextView(): TextView = findViewById(R.id.roll_result_text)

    private fun countUp() {

        val rollResult = getRollResultTextView().text

        val rollResultAsInt = rollResult.toString().toIntOrNull() ?: return

        getRollResultTextView().text = (if (rollResultAsInt >= 6) 6 else rollResultAsInt + 1).toString()
    }

    private fun rollDice() {

        val randomNumber = (1..6).random()

        getRollResultTextView().text = randomNumber.toString()
    }
}