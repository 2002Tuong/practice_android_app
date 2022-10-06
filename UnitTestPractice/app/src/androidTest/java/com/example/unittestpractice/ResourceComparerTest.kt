package com.example.unittestpractice

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {

    private lateinit var resourceComparer : ResourceComparer
    @Before
    fun setUp() {
         resourceComparer = ResourceComparer()
    }


    @Test
    fun isEqual() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val res = resourceComparer.isEqual(context,R.string.app_name,"UnitTestPractice")
        Log.d("test","isEqual() is ${ResourceComparer.temp}")
        assertEquals(true,res)
    }

    @Test
    fun isNotEqual() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val res = resourceComparer.isEqual(context,R.string.app_name,"hello")
        Log.d("test","isNotEqual is ${ResourceComparer.temp}")
        assertEquals(false,res)
    }
}