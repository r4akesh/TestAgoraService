package com.example.testmyapplication.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testmyapplication.R

class ActivityA : AppCompatActivity() {
    private lateinit var  textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)
        textView = findViewById(R.id.text1)
        textView.text= "A"
        Log.d("TAG", "onCreate: ActivityA")
        textView.setOnClickListener {
            startActivity(Intent(this@ActivityA,ActivityB::class.java))
        }

          buildString {
            append("Hello, ")
            append("World!")
        }


            kotlin.run {

            }



    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("TAG ghjghjgh", "onNewIntent: ActivityA ")
    }


}