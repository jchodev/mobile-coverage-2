package com.jerry.assessment.features.testonly.play_service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jerry.assessment.designllib.theme.AssessmentTheme
import com.jerry.assessment.features.testonly.play_service.services.BoundService
import kotlinx.coroutines.launch


class ServiceActivity : ComponentActivity() {

    private lateinit var boundService: BoundService
    private var isBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, binder: IBinder?) {
            val binder = binder as BoundService.MyBinder
            boundService = binder.getService()

            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssessmentTheme {

                var process by remember {
                    mutableStateOf(0f)
                }

                val scope = rememberCoroutineScope()

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Button(
                            onClick = {
                                if (isBound) {
                                    scope.launch {
                                        boundService.getProcess().collect {
                                            process = it
                                        }
                                    }
                                }
                            }
                        ) {
                            Text(text = "get something from bound service")
                        }

                        LinearProgressIndicator(progress = process)
                    }


                }
            }
        }
    }


    override fun onStart() {
        super.onStart()

        Intent(this, BoundService::class.java).also {
            bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
    }
}