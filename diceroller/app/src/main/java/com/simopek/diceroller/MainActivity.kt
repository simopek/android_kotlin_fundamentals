package com.simopek.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var currentDiceNumber = 1
    lateinit var diceImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImageView = findViewById(R.id.dice_image)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        val countUpButton: Button = findViewById(R.id.count_up_button)
        countUpButton.setOnClickListener { countUp() }

        updateDiceImage(currentDiceNumber)
    }

    private fun countUp() {

        currentDiceNumber = if (currentDiceNumber >= 6) 6 else currentDiceNumber + 1
        updateDiceImage(currentDiceNumber)
    }

    private fun rollDice() {

        val diceNumber = (1..6).random()
        currentDiceNumber  = diceNumber
        updateDiceImage(diceNumber)
    }

    private fun updateDiceImage(diceNumber: Int) {

        val diceImage = when (diceNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImageView.setImageResource(diceImage)
    }
}