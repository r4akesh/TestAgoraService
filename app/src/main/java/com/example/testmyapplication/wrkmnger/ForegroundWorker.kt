package com.example.testmyapplication.wrkmnger

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextParams
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.testmyapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class ForegroundWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        setForeground(createForegroundInfo())
        kotlin.runCatching {
            runTask()
            Result.success()
        }.getOrElse { Result.failure() }
    }


    //Fake long running task for 60 seconds
    private suspend fun runTask() {
        delay(20000)

        try {
            var vv2 = 10/0
        } catch (e: Exception) {
            Log.e("HomeWrkManger", "delay: 2")
        }

    }


    //Creates notifications for service
    private fun createForegroundInfo(): ForegroundInfo {
        val id = "1225"
        val channelName = "Downloads Notification"
        val title = "Downloading"
        val cancel = "Cancel1"
        val body = "Long running task is running"

        val intent = WorkManager.getInstance(applicationContext)
            .createCancelPendingIntent(getId())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(id, channelName)
        }

        val notification = NotificationCompat.Builder(applicationContext, id)
            .setContentTitle(title)
            .setTicker(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.btn_switch_camera)
            .setOngoing(true)
            .addAction(android.R.drawable.ic_delete, cancel, intent)
            .build()

        return ForegroundInfo(1, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(id: String, channelName: String) {
        notificationManager.createNotificationChannel(
            NotificationChannel(id, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        )
    }
}