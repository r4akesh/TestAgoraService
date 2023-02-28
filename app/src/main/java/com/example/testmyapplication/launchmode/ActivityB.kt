package com.example.testmyapplication.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testmyapplication.R

class ActivityB : AppCompatActivity() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)
        textView = findViewById(R.id.text1)
        textView.text = "B"
        Log.d("TAG", "onCreate ActivityB: ")
        textView.setOnClickListener {
            startActivity(Intent(this@ActivityB,ActivityC::class.java))
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("TAG", "onNewIntent:  ActivityB")
    }


}