package com.example.unittestpractice

import android.content.Context

class ResourceComparer {

    fun isEqual(context: Context, resId: Int, string: String) : Boolean {
        temp ++
        return context.getString(resId) == string
    }
    companion object {
        var temp = 0
    }
}