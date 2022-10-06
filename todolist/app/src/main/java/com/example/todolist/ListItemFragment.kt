package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapter.ToDoListAdapter
import com.example.todolist.database.ToDoItem
import com.example.todolist.databinding.FragmentListItemBinding

class ListItemFragment : Fragment() {
    private lateinit var _binding : FragmentListItemBinding
    private val viewModel :ToDoViewModel by activityViewModels {
        ToDoViewModelFactory(
            (activity?.application as ToDoListApplication
                    ).database.getDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentListItemBinding.inflate( inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ToDoListAdapter {
            viewModel.update(it)
        }

        _binding.recycleView.adapter = adapter
        _binding.recycleView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.deleteItems.observe(viewLifecycleOwner) {  deletedItems ->
            deletedItems.forEach {
                if (it) {
                    _binding.deleteBtn.visibility = View.VISIBLE
                    return@observe
                }
            }
            _binding.deleteBtn.visibility = View.GONE
        }

        _binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_ListItemFragment_to_AddItemFragment)
        }

        _binding.deleteBtn.setOnClickListener {
            viewModel.delete()

        }

    }
}