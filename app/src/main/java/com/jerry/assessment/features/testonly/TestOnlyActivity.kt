package com.jerry.assessment.features.testonly

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jerry.assessment.designllib.theme.AssessmentTheme
import com.jerry.assessment.features.testonly.components.PasscodeKeys
import dagger.hilt.android.AndroidEntryPoint

//https://medium.com/@jerry.cho.dev/viewmodel-with-savedstatehandle-55eb8a96be1d
@AndroidEntryPoint
class TestOnlyActivity : ComponentActivity() {

    private val viewModel by viewModels<TestOnlyViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AssessmentTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column {

//                        OutlinedTextField(
//                            label = { Text(text = "Name (mutableStateOf)") },
//                            value = viewModel.name,
//                            onValueChange = { viewModel.assignName(it) }
//                        )
//
//                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            label = { Text(text = "Name (MutableStateFlow)") },
                            value = viewModel.nameState.collectAsStateWithLifecycle().value,
                            onValueChange = { viewModel.assignNameState(it) }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            label = { Text(text = "Name (nameWithSaveState)") },
                            value = viewModel.nameWithSaveState.collectAsState().value,
                            onValueChange = { viewModel.assignNameWithSaveState(it) }
                        )

                        OutlinedTextField(
                            label = { Text(text = "Name (nameWithSaveState2)") },
                            value = viewModel.namePlus.collectAsStateWithLifecycle(initialValue = "").value,
                            onValueChange = {  }
                        )

                        val handler = LocalUriHandler.current
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = {
                                handler.openUri("https://www.google.com")
                            }
                        ) {
                            Text("Click me")
                        }

                        PasscodeKeys()
                    }
                }
            }
        }
    }

}