package com.example.countdownapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.countdownapp.databinding.FragmentCountTimeBinding


class CountTimeFragment : Fragment() {

    private lateinit var binding : FragmentCountTimeBinding
    private val timeViewModel : TimeViewModel by activityViewModels()
    private lateinit var timer: CountDownTimer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountTimeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timer =object : CountDownTimer(timeViewModel.timeValue,1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {

                val hour : Long = timeViewModel.convertToHour(millisUntilFinished)
                val remain = millisUntilFinished - hour * 60 * 60 *1000
                val min : Long = timeViewModel.convertToMin(remain)
                val remain1 = remain - min * 60 * 1000
                val sec : Long = timeViewModel.convertToSec(remain1)
                binding.showTime.text = "$hour:$min:$sec"
            }

            override fun onFinish() {
                binding.showTime.text="done"
            }
        }.start()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }
}