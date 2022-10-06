package com.example.countdownapp

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.countdownapp.databinding.FragmentStartBinding
import com.google.android.material.timepicker.TimeFormat
import java.sql.Time
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val timeViewModel : TimeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStart.setOnClickListener {
            val value = binding.timeInput.text.toString()
            if(value.isValidInput() && value.isIncorrectFormat()){

                val time = DateFormat.getTimeInstance(DateFormat.MEDIUM).parse(value)
                val time0 = DateFormat.getTimeInstance().parse("00:00:00")
                timeViewModel.setTime(time.time-time0.time)
                Log.d("startFragment","$time")
                Log.d("startFragment","${timeViewModel.timeValue}")
                findNavController().navigate(R.id.action_startFragment_to_countTimeFragment)
            }
        }
    }


    private fun String.isIncorrectFormat(): Boolean{
        if(this.count { it == ':' } != 2 || this.startsWith(':') || this.endsWith(':')){
            binding.textInputLayout.error = "input is incorrect format"
            return false
        }
        return true
    }
    private fun String.isValidInput():Boolean {
        if(this.isEmpty()){
            binding.textInputLayout.error = "input is invalid"
            return false
        }
        return true
    }
}
