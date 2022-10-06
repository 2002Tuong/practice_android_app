package com.example.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoItem(@PrimaryKey(autoGenerate = true) val id : Int = 0,
                    @ColumnInfo(name = "work") val work : String,
                    @ColumnInfo(name = "isDeleted") var checked : Boolean)
