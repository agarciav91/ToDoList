package com.example.todolist

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_to_do.*

class CreateToDo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_to_do)

        saveButton.setOnClickListener {
            var title = ""
            if (importantCheckBox.isChecked) {
                title = "❗ " + titleEditText.text.toString()
            } else {
                title = titleEditText.text.toString()
            }

            var prefs = getSharedPreferences(getString(R.string.shared_pref_name), Context.MODE_PRIVATE)

            var todos = prefs.getStringSet(getString(R.string.todo_strings), setOf()).toMutableSet()
            todos.add(title)

            prefs.edit().putStringSet(getString(R.string.todo_strings), todos).apply()

            // Moves back to previous activity
            finish()
        }
    }
}
