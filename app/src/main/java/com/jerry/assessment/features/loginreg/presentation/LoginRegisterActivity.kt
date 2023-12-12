package com.jerry.assessment.features.loginreg.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jerry.assessment.R
import com.jerry.assessment.designllib.dialog.HmAlertDialog
import com.jerry.assessment.designllib.theme.AssessmentTheme
import com.jerry.assessment.features.loginreg.presentation.components.LoginPage
import com.jerry.assessment.features.loginreg.presentation.components.RegisterPage
import com.jerry.assessment.features.loginreg.presentation.viewmodel.LoginViewModel
import com.jerry.assessment.features.loginreg.presentation.viewmodel.RegisterViewModel

import com.jerry.assessment.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginRegisterActivity : ComponentActivity() {

    enum class Route {
        LOGIN, REGISTER
    }

    private val viewModel by viewModels<LoginViewModel>()

    private val registerViewModel by viewModels<RegisterViewModel>()

    private lateinit var navController: NavHostController

    private val onGotoMainActivity: () -> Unit = {
        startActivity(Intent(this@LoginRegisterActivity, MainActivity::class.java))
        this@LoginRegisterActivity.finish()
    }

    @Composable
    private fun NormalMessageDialog(message: String, onOKClick :() -> Unit){
        NormalMessageDialog(
            message = message,
            onOKClick = onOKClick
        )
    }


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //init mvi
        viewModel.initIntent()
        registerViewModel.initIntent()

        setContent {
            AssessmentTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    navController = rememberNavController()

                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = Route.LOGIN.toString()){

                        //login
                        composable(
                            route = Route.LOGIN.toString()
                        ) {
                            LoginPage(
                                viewModel = viewModel
                            )
                        }

                        composable(
                            route = Route.REGISTER.toString()
                        ) {
                            RegisterPage(
                                viewModel = registerViewModel
                            )
                        }
                    }

                }
            }
        }

        observeViewModelEvents()
    }

    private fun observeViewModelEvents(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.goToRegisterPageStateFlow.collectLatest {
                    it?.let {
                        navController.navigate(Route.REGISTER.toString())
                    }
                }
            }
        }

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.loginUiState.collectLatest {
//                    it.errorMessage?.let {
//                        Toast.makeText(
//                            this@LoginRegisterActivity,
//                            it,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            }
//        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.goToMainActivityStateFlow.collectLatest {
                    it?.let {
                        onGotoMainActivity()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.goToMainActivityStateFlow.collectLatest {
                    it?.let {
                        onGotoMainActivity()
                    }
                }
            }
        }
    }
}