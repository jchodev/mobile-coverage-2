package com.jerry.assessment.features.testonly.play_service.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber

/*
    Usually bondService is used to bound with activity / Fragment
 */
class BackgroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        /*
        In Android services, START_STICKY is a return value that specifies the behavior of the service when it is killed by the system.
        START_NOT_STICKY: The service will not be recreated unless there are pending Intents calling startService() after it has been stopped.
        START_REDELIVER_INTENT: The service will be recreated and the previous Intent will be passed to the onStartCommand() method if the system restarts the service after it has been stopped.
         */

        MainScope().launch {
            while (true) {
                delay(2000)
                Timber.d("BackgroundService::onStartCommand")
            }
        }

        return START_STICKY
    }


}