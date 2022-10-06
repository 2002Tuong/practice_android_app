package com.example.countdownapp

import androidx.lifecycle.ViewModel

class TimeViewModel : ViewModel() {
    private var _timeValue : Long = 0
    val timeValue get() = _timeValue



    fun setTime(milis : Long){
        _timeValue = milis
    }
    fun convertToHour(milis: Long) : Long {
        return milis /1000 /60 /60
    }
    fun convertToMin(milis: Long) : Long {
        return milis / 1000 / 60
    }
    fun convertToSec(milis: Long) : Long {
        return milis / 1000
    }
}