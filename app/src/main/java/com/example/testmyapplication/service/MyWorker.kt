package com.example.testmyapplication.service

import android.content.Context
import androidx.work.WorkerParameters
import androidx.work.ListenableWorker
import com.example.testmyapplication.service.MyService
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.Worker

class MyWorker( private val context: Context,params: WorkerParameters) : Worker(context, params) {
    private val TAG = "MyWorker"
    override fun doWork(): Result {
        Log.d(TAG, "doWork called for: " + this.id)
        Log.d(TAG, "Service Running: " + MyService.isServiceRunning)
//        if (!MyService.isServiceRunning) {
//            Log.d(TAG, "starting service from doWork")
//            val intent = Intent(context, MyService::class.java)
//            ContextCompat.startForegroundService(context, intent)
//        }
        return Result.success()
    }

    override fun onStopped() {
        Log.d(TAG, "onStopped called for: " + this.id)
        super.onStopped()
    }
}