package com.jerry.assessment.features.testonly.play_service.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*
    Usually bondService is used to bound with activity / Fragment
 */
class BoundService : Service() {

    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getService() = this@BoundService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun getProcess(): Flow<Float> {
        var process = 0f

        return flow {
            while (process < 1.0f) {
                process += 0.1f
                delay(1000)
                emit(process)
            }
        }
    }
}