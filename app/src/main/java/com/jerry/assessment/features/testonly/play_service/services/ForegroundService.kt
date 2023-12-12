package com.jerry.assessment.features.testonly.play_service.services

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.jerry.assessment.R

/*
    Usually bondService is used to bound with activity / Fragment
 */
const val channelId = "My_Foreground_Service_id"

class ForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("NewApi")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel(this)
            } else {
                ""
            }
        val channelBuilder = NotificationCompat.Builder(this, channelId)

        val notificationChannel = channelBuilder.setOngoing(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setContentTitle("My Foreground Service Name title")
            .setContentText("My Foreground Service Name text")
            .build()

        startForeground(100, notificationChannel)

        return START_STICKY
    }


}

@RequiresApi(Build.VERSION_CODES.O)
private fun createNotificationChannel(context: Context): String {
    return channelId
}