package com.example.fakephonecall

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.time.Duration
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputView = findViewById<EditText>(R.id.phone_number)
        val button = findViewById<ImageView>(R.id.button_click)

//        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,
//                listOf(android.Manifest.permission.CALL_PHONE).toTypedArray(),100)
//        }


        button.setOnClickListener {
            val phoneNumber = inputView.text.toString()
            if(phoneNumber.isEmpty()) {
                Toast.makeText(this,"no phone number!",Toast.LENGTH_LONG).show()
            }else {
                val intent = Intent(Intent.ACTION_CALL , Uri.parse("tel:$phoneNumber"))
                startActivity(intent)
            }
        }
    }
}