package com.example.todolist

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_complete_to_do.*

class CompleteToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_to_do)

        val todo = intent.extras.getString("TodoItem")
        todoTextView.text = todo

        completeButton.setOnClickListener {
            var prefs = getSharedPreferences(getString(R.string.shared_pref_name), Context.MODE_PRIVATE)
            var todos = prefs.getStringSet(getString(R.string.todo_strings), setOf()).toMutableSet()

            todos.remove(todo)
            prefs.edit().putStringSet(getString(R.string.todo_strings), todos).apply()

            finish()
        }
    }
}
