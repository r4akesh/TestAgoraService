package com.example.testmyapplication.service_old;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyServiceOld extends Service {
   public MyServiceOld () {
   }

   @Override
   public void onCreate() {
      super.onCreate();
   }

   @Nullable
   @Override
   public IBinder onBind(Intent intent) {
      return null;
   }

   @Override
   public int onStartCommand(Intent intent, int flags, int startId){
      onTaskRemoved(intent); // this used for countinue execute in backgroind
      Toast.makeText(getApplicationContext(),"This is a Service running in Background",
      Toast.LENGTH_SHORT).show();
      Log.d("ServiceOLD", "onStartCommand: ");
      return START_STICKY;
   }

   @Override
   public void onTaskRemoved(Intent rootIntent) {
      Intent restartServiceIntent = new Intent(getApplicationContext(),this.getClass());
      restartServiceIntent.setPackage(getPackageName());
      startService(restartServiceIntent);
      Log.d("ServiceOLD", "onTaskRemoved: ");
      super.onTaskRemoved(rootIntent);
   }



   @Override
   public void onDestroy() {
      super.onDestroy();
      Log.d("ServiceOLD", "onDestroy: ");
   }
}