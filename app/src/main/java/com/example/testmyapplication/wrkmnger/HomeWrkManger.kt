package com.example.testmyapplication.wrkmnger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.testmyapplication.R

class HomeWrkManger : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homewrkrmanager_activity)
        WorkManager.getInstance(this)
            .beginUniqueWork("ForegroundWorker", ExistingWorkPolicy.APPEND_OR_REPLACE,
                OneTimeWorkRequest.from(ForegroundWorker::class.java)).enqueue().state
            .observe(this) { state ->
                Log.e("HomeWrkManger1", "ForegroundWorker: $state")
            }
    }
}