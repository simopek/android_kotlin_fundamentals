package com.simopek.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var doneButton: Button
    private lateinit var nicknameEditText: EditText
    private lateinit var nicknameViewText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nicknameEditText = findViewById(R.id.nickname_edit)
        nicknameViewText = findViewById(R.id.nickname_text)
        doneButton = findViewById(R.id.done_button)

        doneButton.setOnClickListener {
            onNewNicknameProvided()
        }

        nicknameViewText.setOnClickListener {
            editNickname()
        }
    }

    private fun onNewNicknameProvided() {

        val nickname = nicknameEditText.text
        if (nickname.isNullOrBlank()) {
            Toast.makeText(this, R.string.ask_for_valid_nickname, Toast.LENGTH_SHORT).show()
            return
        }

        nicknameViewText.text = nickname
        nicknameViewText.visibility = View.VISIBLE
        nicknameEditText.visibility = View.GONE
        doneButton.visibility = View.GONE

        // we get the reference to the soft input window
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        // we call the method by providing it with the context view that is responsible for soft input window
        inputMethodManager?.hideSoftInputFromWindow(nicknameEditText.applicationWindowToken, 0)
    }

    private fun editNickname() {

        nicknameViewText.visibility = View.GONE
        nicknameEditText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE

        nicknameEditText.requestFocus()

        // we get the reference to the soft input window
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        // we call the method by providing it with the context view that is responsible for soft input window
        inputMethodManager?.showSoftInput(nicknameEditText, 0)
    }
}