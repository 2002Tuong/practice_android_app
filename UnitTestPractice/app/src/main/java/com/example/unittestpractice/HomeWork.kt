package com.example.unittestpractice

object HomeWork {

    fun fib(n : Int) : Long {
        if ( n == 0 || n == 1) {
            return  n.toLong()
        }
        var a = 0L
        var b = 1L
        var c = 1L
        (0..n-2).forEach { i ->
            c = a + b
            a = b
            b = c
        }
        return c
    }
    //sai
    fun checkBraces(string: String) : Boolean {
        return string.count { it == '(' } == string.count { it == ')' }
    }
}