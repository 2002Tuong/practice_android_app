package com.example.todolist

import android.app.Application
import com.example.todolist.database.ToDoDatabase

class ToDoListApplication : Application() {
    val database : ToDoDatabase by lazy {
        ToDoDatabase.getDatabase(this)
    }
}