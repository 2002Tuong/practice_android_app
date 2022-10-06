package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
    @Query ("select * from ToDoItem")
    fun getAll() : LiveData<List<ToDoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : ToDoItem)

    @Query("delete from ToDoItem where isDeleted = 1")
    suspend fun delete()

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(item : ToDoItem)

    @Query("select isDeleted from ToDoItem ")
    fun getIsDeleted() : LiveData<List<Boolean>>

}