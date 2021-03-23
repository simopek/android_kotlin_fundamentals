package com.simopek.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private val boxColors = listOf(Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW,
        Color.CYAN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun makeColored(view: View) {

        view.setBackgroundColor(
            when (view.id) {
                R.id.box_one_text, R.id.box_two_text,
                R.id.box_three_text, R.id.box_four_text, R.id.box_five_text -> boxColors.random()
                // the "else" block when the user clicks on any object that is not a box
                else -> Color.LTGRAY
            }
        )
    }

    private fun setListeners() {

        listOf(
            R.id.box_one_text, R.id.box_two_text, R.id.box_three_text,
            R.id.box_four_text, R.id.box_five_text,
            R.id.main_layout
        ).map { findViewById<View>(it) }.forEach {
            it.setOnClickListener { it2 -> makeColored(it2) }
        }
    }
}