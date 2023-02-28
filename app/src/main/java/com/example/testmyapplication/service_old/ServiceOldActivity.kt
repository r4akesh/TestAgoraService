package com.example.testmyapplication.service_old


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testmyapplication.R
import com.example.testmyapplication.service.MyService

// countinue start service in background
class ServiceOldActivity : AppCompatActivity() {
    var button: Button? = null
    var buttonStop: Button? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_old)
       button = findViewById<Button>(R.id.button)
       // buttonStop = findViewById<Button>(R.id.buttonStop)
        button!!.setOnClickListener {
            startForegroundService(Intent(applicationContext, MyServiceOld::class.java))
        }

//        buttonStop!!.setOnClickListener {
//            stopService(Intent(applicationContext, MyServiceOld::class.java))
//        }

    }


}