package com.example.testmyapplication.service

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.testmyapplication.R
import java.util.concurrent.TimeUnit

//https://gist.github.com/varunon9/f2beec0a743c96708eb0ef971a9ff9cd
//How to create an always running service in Android
//1)Create a Foreground Service (MyService.java)
//2)Create a Manifest registered Broadcast Receiver (MyReceiver.java) which will start your Foreground Service
//3)In onDestroy lifecycle of MyService, send a broadcast intent to MyReceiver
//4)Launch the MyService on app start from MainActivity (see step 8)
//5)With above 4 steps, MyService will always get re-started when killed as long as onDestroy of Service gets called
//6)onDestroy method of Service is not always guaranteed to be called and hence it might not get started again
//7)To overcome this step, register a unique periodic Background Job via WorkManager which will restart MyService if not already started
//8)Register this UniquePeriodicWork (this will run every ~16 minutes) from MainActivity and it will also be responsible for the first launch of MyService

class HomeServiceActivity : AppCompatActivity() {

    var TAG = "HomeServiceStatrt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homeservice)
        //startServiceViaWorker()

    }

    fun onStartServiceClick(v: View?) {
        startService()// via service
       //startServiceViaWorker()
        Log.d(TAG, "onStartServiceClick: ")
    }

    fun onStopServiceClick(v: View?) {
        stopService()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy called")
        stopService()
        super.onDestroy()
    }

    fun startService() {
        if (!MyService.isServiceRunning) {
            Log.d(TAG, "startService called")
            val serviceIntent = Intent(this, MyService::class.java)
            ContextCompat.startForegroundService(this, serviceIntent)
        }
    }

    fun stopService() {
        if (MyService.isServiceRunning) {
            Log.d(TAG, "stopService called")
            val serviceIntent = Intent(this, MyService::class.java)
            stopService(serviceIntent)
        }
    }

    fun startServiceViaWorker() {
        Log.d(TAG, "startServiceViaWorker called")
        val UNIQUE_WORK_NAME = "StartMyServiceViaWorker"
        val workManager: WorkManager = WorkManager.getInstance(this)

        // As per Documentation: The minimum repeat interval that can be defined is 15 minutes
        // (same as the JobScheduler API), but in practice 15 doesn't work. Using 16 here
        val request: PeriodicWorkRequest = PeriodicWorkRequest.Builder(MyWorker::class.java,16, TimeUnit.MINUTES).build()
        // to schedule a unique work, no matter how many times app is opened i.e. startServiceViaWorker gets called
        // do check for AutoStart permission
        workManager.enqueueUniquePeriodicWork(
            UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

}