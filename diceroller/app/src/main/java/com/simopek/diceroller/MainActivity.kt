package com.simopek.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var currentDice1Number = -1
    private var currentDice2Number = -1
    lateinit var dice1ImageView: ImageView
    lateinit var dice2ImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dice1ImageView = findViewById(R.id.dice1_image)
        dice2ImageView = findViewById(R.id.dice2_image)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        val countUpButton: Button = findViewById(R.id.count_up_button)
        countUpButton.setOnClickListener { countUp() }

    }

    private fun incOrNopeDiceNumber(currentDiceNumber: Int) = if (currentDiceNumber >= 6) 6 else if (currentDiceNumber <= 0) 1 else currentDiceNumber + 1

    private fun countUp() {

        currentDice1Number = incOrNopeDiceNumber(currentDice1Number)
        updateDiceImage(currentDice1Number, dice1ImageView)

        currentDice2Number = incOrNopeDiceNumber(currentDice2Number)
        updateDiceImage(currentDice2Number, dice2ImageView)
    }

    private fun rollDice() {

        currentDice1Number = (1..6).random()
        updateDiceImage(currentDice1Number, dice1ImageView)

        currentDice2Number = (1..6).random()
        updateDiceImage(currentDice2Number, dice2ImageView)
    }

    private fun updateDiceImage(diceNumber: Int, diceImageView: ImageView) {

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