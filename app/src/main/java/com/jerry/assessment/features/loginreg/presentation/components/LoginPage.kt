package com.jerry.assessment.features.loginreg.presentation.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import com.jerry.assessment.R
import com.jerry.assessment.data.local.DataStoreManagerImpl

import com.jerry.assessment.designllib.common.textfield.InputTextFieldValidation
import com.jerry.assessment.designllib.dialog.NormalMessageDialog
import com.jerry.assessment.designllib.loading.HmLoading
import com.jerry.assessment.features.loginreg.data.repository.AuthRepositoryImpl
import com.jerry.assessment.features.loginreg.domain.repository.AuthRepository
import com.jerry.assessment.features.loginreg.domain.usecase.LoginUseCase
import com.jerry.assessment.features.loginreg.domain.usecase.ValidateBasicInformationUseCase
import com.jerry.assessment.features.loginreg.presentation.mvi.LoginIntent
import com.jerry.assessment.features.loginreg.presentation.provider.LocalizedProvider

import com.jerry.assessment.features.loginreg.presentation.viewmodel.LoginViewModel



@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel
) {

    val loginUiState = viewModel.loginUiState.collectAsStateWithLifecycle().value
    val dialogMessage = viewModel.dialogMessageState.collectAsStateWithLifecycle().value

    val loadCompose = @Composable {
        HmLoading()
    }

    LoginPageContent (
        modifier = modifier,
        emailValue = loginUiState.emailValue,
        emailError = loginUiState.emailError,

        passwordValue = loginUiState.passwordValue,
        passwordError = loginUiState.passwordError,

        onEmailValueChange = {
            viewModel.sendIntent(LoginIntent.EmailChanged(it))
        },
        onPasswordValueChange = {
            viewModel.sendIntent(LoginIntent.PasswordChanged(it))
        },
        isPasswordVisible = loginUiState.isVisiblePassword,
        onPasswordTrailingIconClick = {
            viewModel.sendIntent(LoginIntent.VisiblePassword(it))
        },
        onSubmit = {
            viewModel.sendIntent(LoginIntent.Login)
        },
        onForgotPassword = {
            viewModel.sendIntent(LoginIntent.ForgotPassword)
        },
        onCreateAccount = {
            viewModel.sendIntent(LoginIntent.CreateAccount)
        },

        visibleLoading = loginUiState.isLoading,
        loadingCompose = loadCompose,

        dialogMessage = dialogMessage,
        onDialogOK = {
            viewModel.sendIntent(LoginIntent.DismissDialog)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPageContent(
    modifier: Modifier = Modifier,
    emailValue: String,
    emailError: String?,
    passwordValue: String,
    passwordError: String?,
    isPasswordVisible : Boolean,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onPasswordTrailingIconClick: (Boolean) -> Unit,
    onSubmit: () -> Unit,
    onForgotPassword: () -> Unit,
    onCreateAccount: () -> Unit,

    visibleLoading: Boolean,
    loadingCompose: @Composable () -> Unit,

    dialogMessage: String?,
    onDialogOK: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)   // add a border (optional)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Login",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputTextFieldValidation (
                placeholder = "Email",
                keyboardType = KeyboardType.Email,
                text = emailValue,
                error = emailError,
                onValueChange = {
                    onEmailValueChange(it)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            InputTextFieldValidation (
                placeholder = "Password",
                keyboardType = KeyboardType.Password,
                trailingIcon = {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        IconButton(
                            onClick =
                            {
                                onPasswordTrailingIconClick(!isPasswordVisible)
                            }
                        ) {
                            Icon(
                                painter = if (isPasswordVisible) painterResource(
                                    id = R.drawable.ic_visible
                                ) else painterResource(
                                    id = R.drawable.ic_invisible
                                ),
                                contentDescription = "Visible",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                },
                text = passwordValue,
                error = passwordError,
                onValueChange = {
                    onPasswordValueChange(it)
                },
                isVisible = isPasswordVisible
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onSubmit,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(

                onClick = { onForgotPassword() },
            ) {
                Text(text = "Forgot password?")
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = { onCreateAccount() },
                modifier = Modifier
                    .fillMaxWidth(),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
            ) {
                Text("Create Account")
            }
        }

        dialogMessage?.let {
            NormalMessageDialog (
                message = it,
                onOKClick = onDialogOK
            )
        }

        if (visibleLoading) {
            loadingCompose()
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPagePreview() {
    val provider = LocalizedProvider(
        LocalContext.current
    )
    MaterialTheme {
        LoginPage(
            viewModel = LoginViewModel(
                validateBasicInformationUseCase = ValidateBasicInformationUseCase(provider),
                savedStateHandle = SavedStateHandle(),
                loginUseCase = LoginUseCase(
                    DataStoreManagerImpl(LocalContext.current),
                    AuthRepositoryImpl(
                        FirebaseAuth.getInstance(),
                        Firebase.firestore
                    ),
                    provider
                )
            )
        )
    }
}