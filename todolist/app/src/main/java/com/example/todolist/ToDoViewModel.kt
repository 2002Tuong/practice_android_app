package com.example.todolist

import android.content.ClipData
import androidx.lifecycle.*
import com.example.todolist.database.ToDoDao
import com.example.todolist.database.ToDoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(private val toDoDao : ToDoDao) : ViewModel() {
    var allItems : LiveData<List<ToDoItem>> = toDoDao.getAll()
    val deleteItems : LiveData<List<Boolean>> = toDoDao.getIsDeleted()
        /*Transformations.map(allItems) { list ->
        list.map {
            it.checked
        }
    }*/
    private fun insert(item : ToDoItem) {
        viewModelScope.launch {
            toDoDao.insert(item)
        }
    }

    private fun getNewItem(toDo : String,isChecked : Boolean = false) : ToDoItem = ToDoItem(
        work = toDo,
        checked = isChecked)

    fun addItem(toDo: String) {
        val newItem = getNewItem(toDo)
        insert(newItem)
    }


    fun delete() = viewModelScope.launch {
        toDoDao.delete()
    }


    fun update(item : ToDoItem) = viewModelScope.launch(Dispatchers.IO) {
        toDoDao.update(item)
    }
}

class ToDoViewModelFactory(private val toDoDao : ToDoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ToDoViewModel(toDoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}