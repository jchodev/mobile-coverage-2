package com.jerry.assessment.features.loginreg.presentation.components

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.jerry.assessment.common.utils.MoshiParser
import com.jerry.assessment.data.local.DataStoreManagerImpl
import com.jerry.assessment.designllib.common.textfield.InputTextFieldValidation
import com.jerry.assessment.designllib.dialog.NormalMessageDialog
import com.jerry.assessment.designllib.loading.HmLoading
import com.jerry.assessment.domain.manager.DataStoreManager
import com.jerry.assessment.domain.usecase.LoginDataUseCase
import com.jerry.assessment.features.loginreg.data.repository.AuthRepositoryImpl
import com.jerry.assessment.features.loginreg.data.repository.FirestoreRepositoryImpl
import com.jerry.assessment.features.loginreg.domain.usecase.LoginUseCase
import com.jerry.assessment.features.loginreg.domain.usecase.RegisterUseCase
import com.jerry.assessment.features.loginreg.domain.usecase.ValidateBasicInformationUseCase
import com.jerry.assessment.features.loginreg.presentation.mvi.LoginIntent
import com.jerry.assessment.features.loginreg.presentation.mvi.RegisterIntent

import com.jerry.assessment.features.loginreg.presentation.provider.LocalizedProvider
import com.jerry.assessment.features.loginreg.presentation.viewmodel.LoginViewModel
import com.jerry.assessment.features.loginreg.presentation.viewmodel.RegisterViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel
) {

    val loadCompose = @Composable {
        HmLoading()
    }

    val registerUiState = viewModel.registerUiState.collectAsStateWithLifecycle().value
    val dialogMessage = viewModel.dialogMessageState.collectAsStateWithLifecycle().value
    
    RegisterPageContent(
        modifier = modifier,

        emailValue = registerUiState.emailValue,
        emailError = registerUiState.emailError,
        onEmailValueChange = {
            viewModel.sendIntent(
                RegisterIntent.EmailChanged(it)
            )
        },

        passwordValue = registerUiState.passwordValue,
        passwordError = registerUiState.passwordError,
        isPasswordVisible = registerUiState.isVisiblePassword,
        onPasswordValueChange = {
            viewModel.sendIntent(RegisterIntent.PasswordChanged(it))
        },
        onPasswordTrailingIconClick = {
            viewModel.sendIntent(RegisterIntent.VisiblePassword(it))
        },

        confirmPasswordValue = registerUiState.confirmPasswordValue,
        confirmPasswordError = registerUiState.confirmPasswordError,
        isConfirmPasswordVisible = registerUiState.isVisibleConfirmPassword,
        onConfirmPasswordValueChange = {
            viewModel.sendIntent(RegisterIntent.ConfirmPasswordChanged(it))
        },
        onConfirmPasswordTrailingIconClick = {
            viewModel.sendIntent(RegisterIntent.VisibleConfirmPassword(it))
        },

        onSignUp = {
            viewModel.sendIntent(RegisterIntent.Register)
        },


        visibleLoading = registerUiState.isLoading,

        loadingCompose = loadCompose,
        dialogMessage = dialogMessage,
        onDialogOK = {
            viewModel.sendIntent(RegisterIntent.DismissDialog)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPageContent(
    modifier: Modifier = Modifier,

    emailValue: String,
    emailError: String?,
    onEmailValueChange: (String) -> Unit,

    passwordValue: String,
    passwordError: String?,
    isPasswordVisible : Boolean,
    onPasswordValueChange: (String) -> Unit,
    onPasswordTrailingIconClick: (Boolean) -> Unit,

    confirmPasswordValue: String,
    confirmPasswordError: String?,
    isConfirmPasswordVisible : Boolean,
    onConfirmPasswordValueChange: (String) -> Unit,
    onConfirmPasswordTrailingIconClick: (Boolean) -> Unit,

    onSignUp: () -> Unit,

    visibleLoading: Boolean,
    loadingCompose: @Composable () -> Unit,

    dialogMessage: String?,
    onDialogOK: () -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
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
                text = "Create an Account",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(8.dp))

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
            InputTextFieldValidation (
                placeholder = "Confirm Password",
                keyboardType = KeyboardType.Password,
                trailingIcon = {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        IconButton(
                            onClick =
                            {
                                onConfirmPasswordTrailingIconClick(!isConfirmPasswordVisible)
                            }
                        ) {
                            Icon(
                                painter = if (isConfirmPasswordVisible) painterResource(
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
                text = confirmPasswordValue,
                error = confirmPasswordError,
                onValueChange = {
                    onConfirmPasswordValueChange(it)
                },
                isVisible = isConfirmPasswordVisible
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onSignUp,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Register")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "By signing up, you agree to our Terms and Conditions",
                style = TextStyle(fontSize = 12.sp),
            )
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
private fun RegisterPagePreview() {
    val provider = LocalizedProvider(
        LocalContext.current
    )
    MaterialTheme {
        RegisterPage(
            viewModel = RegisterViewModel(
                validateBasicInformationUseCase = ValidateBasicInformationUseCase(provider),
                savedStateHandle = SavedStateHandle(),
                registerUseCase = RegisterUseCase(
                    dataStoreManager = DataStoreManagerImpl(LocalContext.current),
                    authRepository = AuthRepositoryImpl(
                        FirebaseAuth.getInstance(),
                    ),
                    firestoreRepository = FirestoreRepositoryImpl(
                        Firebase.firestore
                    ),
                    localizedProvider = provider
                )
            )
        )
    }
}