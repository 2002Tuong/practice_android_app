package com.example.testcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testcomponent.databinding.FragmentBlank3Binding
import com.example.testcomponent.databinding.FragmentBlank4Binding


class BlankFragment4 : Fragment() {

    private lateinit var binding : FragmentBlank4Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_blank4,container,false)
        return binding.root
    }

}