package com.jerry.assessment.features.splash.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jerry.assessment.R
import com.jerry.assessment.features.loginreg.presentation.LoginRegisterActivity
import com.jerry.assessment.features.mobilecoverage.presentation.viewmodel.MobileCoverageViewModel
import com.jerry.assessment.features.splash.presentation.viewmodel.SplashViewModel
import com.jerry.assessment.features.testonly.play_service.services.BackgroundService
import com.jerry.assessment.features.testonly.play_service.services.ForegroundService
import com.jerry.assessment.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel by viewModels<SplashViewModel>()


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.checkLogin()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isCheckedWithFirebase.value != null
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isCheckedWithFirebase.collectLatest {
                    it?.let {
                        startActivity(
                            Intent(
                                this@SplashActivity,
                                LoginRegisterActivity::class.java
                            )
                        )
                        //startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        this@SplashActivity.finish()
                    }

                }
            }
        }


        //start background service
        Intent(this, BackgroundService::class.java).also {
            startService(it)
        }

        //start background service
//        Intent(this, ForegroundService::class.java).also {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//                startForegroundService(it)
//        }

    }

    override fun onDestroy() {
        //start background service
        Intent(this, BackgroundService::class.java).also {
            stopService(it)
        }
        super.onDestroy()
    }
}