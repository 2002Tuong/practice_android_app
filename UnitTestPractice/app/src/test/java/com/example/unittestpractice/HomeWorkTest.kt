package com.example.unittestpractice



import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*
import org.junit.Test

class HomeWorkTest {
    @Test
    fun `result on value 1 `() {
        val res = HomeWork.fib(1)

        assertEquals(1,res)
    }

    @Test
    fun `result on value 0 `() {
        val res = HomeWork.fib(0)

        assertThat(res).isEqualTo(0)
    }

    @Test
    fun `result on value 10`() {
        val res = HomeWork.fib(10)

        assertThat(res).isEqualTo(55)
    }

    @Test
    fun `check_braces_of_(a*b))`() {
        val res = HomeWork.checkBraces("(a*b))")

        assertThat(res).isEqualTo(false)
    }
}