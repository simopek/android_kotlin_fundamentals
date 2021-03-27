package com.simopek.colormyviews

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var buttonsColorRGB = intArrayOf(255, 255, 255)

    private lateinit var redButton: Button
    private lateinit var greenButton: Button
    private lateinit var blueButton: Button

    private var pressedStateByButton = mutableMapOf<Int, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redButton = findViewById(R.id.red_button)
        greenButton = findViewById(R.id.green_button)
        blueButton = findViewById(R.id.blue_button)

        listOf(redButton, greenButton, blueButton).map { it.id }.forEach { pressedStateByButton[it] = false }

        setListeners()
    }

    private fun makeColored(view: View) {

        view.setBackgroundColor(
            when (view.id) {
                R.id.box_one_text, R.id.box_two_text,
                R.id.box_three_text, R.id.box_four_text, R.id.box_five_text -> randomColor()
                // the "else" block when the user clicks on any object that is not a box
                else -> Color.LTGRAY
            }
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListeners() {

        listOf(
            R.id.box_one_text, R.id.box_two_text, R.id.box_three_text,
            R.id.box_four_text, R.id.box_five_text,
            R.id.main_layout
        ).map { findViewById<View>(it) }.forEach {
            it.setOnClickListener { it2 -> makeColored(it2) }
        }

        redButton.setOnTouchListener { button, motionEvent ->
            handleColorButtonOnTouch(button, motionEvent)
            return@setOnTouchListener true
        }

        greenButton.setOnTouchListener { button, motionEvent ->
            handleColorButtonOnTouch(button, motionEvent)
            return@setOnTouchListener true
        }

        blueButton.setOnTouchListener { button, motionEvent ->
            handleColorButtonOnTouch(button, motionEvent)
            return@setOnTouchListener true
        }
    }

    private fun randomColor(): Int = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

    private fun addMoreRed() {
        buttonsColorRGB[0] = (buttonsColorRGB[0] + 10) % 255
    }

    private fun addMoreGreen() {
        buttonsColorRGB[1] = (buttonsColorRGB[1] + 10) % 255
    }

    private fun addMoreBlue() {
        buttonsColorRGB[2] = (buttonsColorRGB[2] + 10) % 255
    }

    private fun updateButtonsColor() {

        val newColor = Color.rgb(buttonsColorRGB[0], buttonsColorRGB[1], buttonsColorRGB[2])
        listOf(R.id.red_button, R.id.green_button, R.id.blue_button).forEach { findViewById<View>(it).setBackgroundColor(newColor) }
    }

    private fun handleColorButtonOnTouch(button: View, motionEvent: MotionEvent) {

        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> pressedStateByButton[button.id] = true
            MotionEvent.ACTION_UP -> pressedStateByButton[button.id] = false
        }

        if (pressedStateByButton[button.id]!!) {

            when(button.id) {
                redButton.id -> addMoreRed()
                greenButton.id -> addMoreGreen()
                blueButton.id -> addMoreBlue()
            }
        }

        updateButtonsColor()
    }
}