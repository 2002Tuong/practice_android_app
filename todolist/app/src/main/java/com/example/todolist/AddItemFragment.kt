package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.adapter.ToDoListAdapter
import com.example.todolist.databinding.FragmentAddItemBinding

class AddItemFragment : Fragment() {
    private lateinit var  _binding : FragmentAddItemBinding
    private val viewModel : ToDoViewModel by activityViewModels {
        ToDoViewModelFactory(
            (activity?.application as ToDoListApplication).database.getDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.cancelBtn.setOnClickListener {
            findNavController().navigate(R.id.action_AddItemFragment_to_ListItemFragment)
        }

        _binding.addBtn.setOnClickListener {
            if(isEntryValid())
            {
                viewModel.addItem(_binding.textInput.text.toString())
            }
            findNavController().navigate(R.id.action_AddItemFragment_to_ListItemFragment)
        }

    }
    private fun isEntryValid() : Boolean = _binding.textInput.toString().isNotEmpty()
}