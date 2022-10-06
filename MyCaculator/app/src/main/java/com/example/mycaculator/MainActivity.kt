package com.example.mycaculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.helper.widget.MotionEffect.AUTO
import com.example.mycaculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val listButtons = createListButton()
        val listLinearLayout = createListLinearLayout()
        matchButtonToLayout(listLinearLayout,listButtons)
        for (linea in listLinearLayout){
            binding.root.addView(linea)
        }
        setListeners()

    }

    private fun setListeners() {
        val listOfBtn : List<View>  = listOf(binding.btn1,binding.btn2,binding.btn3)
    }

    private fun createListLinearLayout(): List<LinearLayout> {
        val list = mutableListOf<LinearLayout>()
        for (i in 1..6 ) list.add(LinearLayout(this))
        return list
    }

    private fun createListButton() : List<Button> {
        val listButton = mutableListOf<Button>()
        val signals = listOf("%","CE","C","DEL",
                            "1/x","x^2","sqrt(x)","/",
                            "7","8","9","X",
                            "4","5","6","-",
                            "1","2","3","+",
                            "+/-","0",".","=")
        for (signal in signals) {
            val button = Button(this)
            button.text = signal

            listButton.add(button)
        }
        return listButton
    }

    private fun matchButtonToLayout(list1 : List<LinearLayout>, list2 : List<Button>) {
        var countButton = 0
        for ((count, linearLayout) in list1.withIndex()) {
            while (countButton / 4 == count) {
                linearLayout.addView(list2[countButton])
                countButton++
            }
        }
    }
}