package com.jerry.assessment.features.loginreg.presentation.viewmodel


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jerry.assessment.R
import com.jerry.assessment.base.presentation.mvi.BaseViewModel
import com.jerry.assessment.base.usecase.UseCaseResult
import com.jerry.assessment.domain.manager.DataStoreManager
import com.jerry.assessment.features.LoginUIState
import com.jerry.assessment.features.loginreg.domain.usecase.LoginUseCase
import com.jerry.assessment.features.loginreg.domain.usecase.ValidateBasicInformationUseCase
import com.jerry.assessment.features.loginreg.presentation.mvi.LoginAction
import com.jerry.assessment.features.loginreg.presentation.mvi.LoginIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


const val LOGIN_UI_STATE_KEY = "login_ui_state"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val validateBasicInformationUseCase: ValidateBasicInformationUseCase,
    private val loginUseCase: LoginUseCase
): BaseViewModel<LoginIntent, LoginAction>() {

    val loginUiState = savedStateHandle.getStateFlow(LOGIN_UI_STATE_KEY, LoginUIState())

    //---- go to registerPage
    private val _goToRegisterPageStateFlow = MutableStateFlow<Boolean?>(null)
    val goToRegisterPageStateFlow = _goToRegisterPageStateFlow.asStateFlow()

    private val _goToMainActivityStateFlow = MutableStateFlow<Boolean?>(null)
    val goToMainActivityStateFlow = _goToMainActivityStateFlow.asStateFlow()

    //----- Dialog Message
    private val _dialogMessageState = MutableStateFlow<String?>(null)
    val dialogMessageState = _dialogMessageState.asStateFlow()

    override suspend fun handleIntent(intent: LoginIntent): List<LoginAction> {
        return when (intent){
            is LoginIntent.VisiblePassword -> {
                listOf(
                    LoginAction.VisiblePassword(intent.isVisiblePassword)
                )
            }
            is LoginIntent.EmailChanged -> {
                listOf(
                    LoginAction.AssignEmailValue(intent.email)
                )
            }
            is LoginIntent.PasswordChanged -> {
                listOf(
                    LoginAction.AssignPasswordValue(intent.password)
                )
            }
            is LoginIntent.Login -> {
                listOf(
                    LoginAction.Login
                )
            }
            is LoginIntent.ForgotPassword -> {
                listOf(
                    LoginAction.GoToForgotPassword
                )
            }
            is LoginIntent.CreateAccount -> {
                listOf(
                    LoginAction.GoToCreateAccount
                )
            }
            is LoginIntent.DismissDialog -> {
                listOf(
                    LoginAction.DismissDialog
                )
            }
        }
    }

    override suspend fun handleAction(action: LoginAction) {
        when (action){
            is LoginAction.AssignEmailValue -> {
                savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                    emailValue = action.email
                )
            }
            is LoginAction.AssignPasswordValue -> {
                savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                    passwordValue = action.password
                )
            }
            is LoginAction.VisiblePassword -> {
                savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                    isVisiblePassword = action.visiblePassword
                )
            }
            is LoginAction.Login -> {
                clearError()
                login()
            }
            is LoginAction.GoToForgotPassword -> {
                clearError()
                forgetPassword()
            }
            is LoginAction.GoToCreateAccount -> {
                if (_goToRegisterPageStateFlow.value == null){
                    _goToRegisterPageStateFlow.value = false
                }
                else {
                    _goToRegisterPageStateFlow.value = !_goToRegisterPageStateFlow.value!!
                }
            }
            is LoginAction.DismissDialog -> {
                _dialogMessageState.value = null
            }
        }
    }

    override suspend fun handleIntentActionsTracker(
        intent: LoginIntent,
        actions: List<LoginAction>
    ) {
        //TODO("Not yet implemented")
    }

    private fun clearError(){
        savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
            emailError = null,
            passwordError = null
        )
        _dialogMessageState.value = null
    }

    private fun login(){
        //show loading
        savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
            isLoading = true,
        )

        //validate email
        val emailError = validateBasicInformationUseCase.validateEmail(loginUiState.value.emailValue)
        emailError?.let {
            savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                emailError = it,
                isLoading = false
            )
            return
        }

        //validate email
        val passwordError = validateBasicInformationUseCase.validatePassword(loginUiState.value.passwordValue)
        passwordError?.let {
            savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                passwordError = it,
                isLoading = false
            )
            return
        }

        //login with server / firebase
        viewModelScope.launch {

            when (val result = loginUseCase.invoke(email = loginUiState.value.emailValue, password = loginUiState.value.passwordValue)) {
                is UseCaseResult.Failure -> {
                    savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                        isLoading = false
                    )
                    _dialogMessageState.value = "Failure"
                }
                is UseCaseResult.Success -> {
                    savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                        isLoading = false
                    )
                    _goToMainActivityStateFlow.value = true
                }
                is UseCaseResult.CustomerError -> {
                    savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                        isLoading = false
                    )
                    _dialogMessageState.value = result.error
                }

                else -> {}
            }
        }
    }

    private fun forgetPassword(){
        //show loading
        savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
            isLoading = true,
        )

        //validate email
        val emailError = validateBasicInformationUseCase.validateEmail(loginUiState.value.emailValue)
        emailError?.let {
            savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                emailError = it,
                isLoading = false
            )
            return
        }


        //work with server / firebase
        viewModelScope.launch {

            when (val result = loginUseCase.forgetPassword(email = loginUiState.value.emailValue)) {

                is UseCaseResult.Failure -> {
                    savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                        isLoading = false
                    )
                    _dialogMessageState.value = "Failure"
                }
                is UseCaseResult.Success -> {
                    savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                        isLoading = false,
                    )
//                    _goToMainActivityStateFlow.value = false
                    _dialogMessageState.value = result.data!!
                }
                is UseCaseResult.CustomerError -> {
                    savedStateHandle[LOGIN_UI_STATE_KEY] = loginUiState.value.copy(
                        isLoading = false
                    )
                    _dialogMessageState.value = result.error
                }
                else -> {

                }

            }
        }
    }
}