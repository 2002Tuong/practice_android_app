package com.example.testcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.testcomponent.databinding.FragmentBlank1Binding
import com.example.testcomponent.databinding.FragmentBlank2Binding


class BlankFragment2 : Fragment() {

    private lateinit var binding: FragmentBlank2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_blank2,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            it.findNavController().navigate(BlankFragment2Directions.actionBlankFragment2ToBlankFragment3())
        }
    }

}