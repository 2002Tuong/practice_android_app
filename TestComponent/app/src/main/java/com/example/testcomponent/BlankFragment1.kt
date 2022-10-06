package com.example.testcomponent

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.testcomponent.databinding.ActivityMainBinding
import com.example.testcomponent.databinding.FragmentBlank1Binding


class BlankFragment1 : Fragment() {

    private lateinit var binding : FragmentBlank1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_blank1,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            it.findNavController().navigate(BlankFragment1Directions.actionBlankFragment1ToBlankFragment2())
        }
    }
}