package com.example.todolist

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class ToDoAdapter(val todoStrings: List<String>) : RecyclerView.Adapter<ToDoAdapter.todoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): todoHolder {
        return todoHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_row, parent, false))
    }

    override fun getItemCount(): Int {
        return todoStrings.count()
    }

    override fun onBindViewHolder(holder: ToDoAdapter.todoHolder, position: Int) {
        val toDo = todoStrings[position]
        holder.bindTodo(toDo)
    }

    class todoHolder(v : View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        var view : View = v
        var todoItem : String = ""

        init {
            v.setOnClickListener(this)
        }

        fun bindTodo (toDo : String) {
            this.todoItem = toDo
            view.todoItemTextView.text = toDo
        }

        override fun onClick(v: View?) {
            val completeTodo = Intent(view.context, CompleteToDoActivity::class.java)
            completeTodo.putExtra("TodoItem", todoItem)
            startActivity(view.context, completeTodo, null)
        }

    }
}