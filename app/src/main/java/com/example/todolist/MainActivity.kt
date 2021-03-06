package com.example.todolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager : LinearLayoutManager
    lateinit var adapter : ToDoAdapter

    //var todos : MutableSet<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val creatingAToDo = Intent(this, CreateToDo::class.java)
            startActivity(creatingAToDo)

        }
    }

    override fun onResume() {
        super.onResume()

        updateRecycler()
    }

    fun updateRecycler() {

        var prefs = getSharedPreferences(getString(R.string.shared_pref_name), Context.MODE_PRIVATE)
        var todos = prefs.getStringSet(getString(R.string.todo_strings), setOf()).toMutableSet()

        //prefs.edit().clear().apply()

        // Layout Manager
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // Adapter
        adapter = ToDoAdapter(todos.toList())
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            if (item.itemId == R.id.action_delete_all) {

                var prefs = getSharedPreferences(getString(R.string.shared_pref_name), Context.MODE_PRIVATE)
                prefs.edit().putStringSet(getString(R.string.todo_strings), null).apply()
                updateRecycler()
                return true
            }

            return super.onOptionsItemSelected(item)
        }
}
