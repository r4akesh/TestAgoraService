package com.example.testmyapplication.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testmyapplication.R

class ActivityC : AppCompatActivity() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)
        textView = findViewById(R.id.text1)
        textView.text = "C"
        Log.d("TAG", "onCreate: ActivityC ")
        textView.setOnClickListener {
            startActivity(Intent(this@ActivityC,ActivityB::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("TAG", "onNewIntent: ActivityC ")
    }
}