package com.example.todolist.adapter

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.ToDoItem
import com.example.todolist.databinding.ListItemBinding
import java.util.*

const val TAG = "test"

class ToDoListAdapter(private val updateItem : (ToDoItem) -> Unit) :ListAdapter<ToDoItem,ToDoListAdapter.ListItemViewHolder>(diffCallBack) {

    private val itemStateArray = SparseBooleanArray()

    class ListItemViewHolder( var binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ToDoItem) {
            binding.checkBoxItem.isChecked = item.checked
            binding.textViewItem.text = item.work
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item)
        holder.binding.apply {
            /*checkBoxItem.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(textViewItem,isChecked)
                item.checked = !item.checked
                Log.d(TAG,"${item.work} and is deleted : ${item.checked}")
                updateItem(item)

            }*/

            checkBoxItem.setOnClickListener {
                item.checked = !item.checked
                //toggleStrikeThrough(textViewItem,item.checked)
                updateItem(item)
            }

        }

    }

    private fun toggleStrikeThrough(textView : TextView, isChecked : Boolean) {
        if(isChecked) {
            textView.paintFlags = textView.paintFlags or  STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and  STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<ToDoItem>() {

            override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
                return oldItem == newItem
            }
        }

    }


}