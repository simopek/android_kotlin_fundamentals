package com.simopek.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    private fun setListeners() {

        listOf(
            R.id.box_one_text, R.id.box_two_text, R.id.box_three_text,
            R.id.box_four_text, R.id.box_five_text,
            R.id.main_layout
        ).map { findViewById<View>(it) }.forEach {
            it.setOnClickListener { it2 -> makeColored(it2) }
        }
    }

    private fun randomColor(): Int = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
}