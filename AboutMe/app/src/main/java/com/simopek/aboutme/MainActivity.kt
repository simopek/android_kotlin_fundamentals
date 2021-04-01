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
import androidx.databinding.DataBindingUtil
import com.simopek.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName = MyName("Sim")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = this.myName
        binding.apply {

            doneButton.setOnClickListener {
                onNewNicknameProvided()
            }

            nicknameText.setOnClickListener {
                editNickname()
            }
        }

    }

    private fun onNewNicknameProvided() {

        val nickname = binding.nicknameEdit.text
        if (nickname.isNullOrBlank()) {
            Toast.makeText(this, R.string.ask_for_valid_nickname, Toast.LENGTH_SHORT).show()
            return
        }

        binding.let {

            this.myName.nickname = nickname.toString()
            // since we update the binding's "data" we have to invalidate it
            // in order to refresh the view
            it.invalidateAll()

            it.nicknameText.visibility = View.VISIBLE
            it.nicknameEdit.visibility = View.GONE
            it.doneButton.visibility = View.GONE

            // we get the reference to the soft input window
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            // we call the method by providing it with the context view that is responsible for soft input window
            inputMethodManager?.hideSoftInputFromWindow(it.nicknameEdit.applicationWindowToken, 0)
        }
    }

    private fun editNickname() {

        binding.apply {
            nicknameText.visibility = View.GONE
            nicknameEdit.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE

            nicknameEdit.requestFocus()

            // we get the reference to the soft input window
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            // we call the method by providing it with the context view that is responsible for soft input window
            inputMethodManager?.showSoftInput(nicknameEdit, 0)
        }
    }
}