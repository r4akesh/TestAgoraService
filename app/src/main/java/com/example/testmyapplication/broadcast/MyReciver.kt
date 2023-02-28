package com.example.testmyapplication.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyReciver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent!!.getStringExtra("message")
        Log.d("receiver onReceive", "Got message1: $message")
    }
}