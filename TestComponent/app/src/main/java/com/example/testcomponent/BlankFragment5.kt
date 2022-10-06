package com.example.testcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testcomponent.databinding.FragmentBlank4Binding
import com.example.testcomponent.databinding.FragmentBlank5Binding

class BlankFragment5 : Fragment() {

    private lateinit var binding: FragmentBlank5Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_blank5,container,false)
        return binding.root
    }



}