package com.jerry.assessment.features.loginreg.presentation.viewmodel


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope

import com.jerry.assessment.base.presentation.mvi.BaseViewModel
import com.jerry.assessment.base.usecase.UseCaseResult
import com.jerry.assessment.domain.usecase.LoginDataUseCase
import com.jerry.assessment.features.RegisterUIState
import com.jerry.assessment.features.loginreg.domain.usecase.RegisterUseCase
import com.jerry.assessment.features.loginreg.domain.usecase.ValidateBasicInformationUseCase
import com.jerry.assessment.features.loginreg.presentation.mvi.LoginAction
import com.jerry.assessment.features.loginreg.presentation.mvi.LoginIntent
import com.jerry.assessment.features.loginreg.presentation.mvi.RegisterAction
import com.jerry.assessment.features.loginreg.presentation.mvi.RegisterIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


const val REGISTER_UI_STATE_KEY = "register_ui_state"

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val validateBasicInformationUseCase: ValidateBasicInformationUseCase,
    private val registerUseCase: RegisterUseCase
): BaseViewModel<RegisterIntent, RegisterAction>() {

    val registerUiState = savedStateHandle.getStateFlow(REGISTER_UI_STATE_KEY, RegisterUIState())

    private val _goToMainActivityStateFlow = MutableStateFlow<Boolean?>(null)
    val goToMainActivityStateFlow = _goToMainActivityStateFlow.asStateFlow()

    //----- Dialog Message
    private val _dialogMessageState = MutableStateFlow<String?>(null)
    val dialogMessageState = _dialogMessageState.asStateFlow()

    override suspend fun handleIntent(intent: RegisterIntent): List<RegisterAction> {
        return when (intent){

            //email
            is RegisterIntent.EmailChanged -> {
                listOf(
                    RegisterAction.AssignEmailValue(intent.email)
                )
            }

            //password
            is RegisterIntent.PasswordChanged -> {
                listOf(
                    RegisterAction.AssignPasswordValue(intent.password)
                )
            }
            is RegisterIntent.VisiblePassword -> {
                listOf(
                    RegisterAction.VisiblePassword(intent.isVisiblePassword)
                )
            }

            //confirm password
            is RegisterIntent.ConfirmPasswordChanged -> {
                listOf(
                    RegisterAction.AssignConfirmPasswordValue(intent.password)
                )
            }
            is RegisterIntent.VisibleConfirmPassword -> {
                listOf(
                    RegisterAction.VisibleConfirmPassword(intent.isVisiblePassword)
                )
            }

            is RegisterIntent.Register -> {
                listOf(
                    RegisterAction.Register
                )
            }

            is RegisterIntent.DismissDialog -> {
                listOf(
                    RegisterAction.DismissDialog
                )
            }
        }
    }

    override suspend fun handleAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.AssignEmailValue -> {
                savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                    emailValue = action.email
                )
            }

            is RegisterAction.AssignPasswordValue -> {
                savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                    passwordValue = action.password
                )
            }
            is RegisterAction.VisiblePassword -> {
                savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                    isVisiblePassword = action.visiblePassword
                )
            }

            //confirm password
            is RegisterAction.AssignConfirmPasswordValue -> {
                savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                    confirmPasswordValue = action.confirmPassword
                )
            }
            is RegisterAction.VisibleConfirmPassword -> {
                savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                    isVisibleConfirmPassword = action.visibleConfirmPassword
                )
            }

            is RegisterAction.Register -> {
                clearError()
                register()
            }

            is RegisterAction.DismissDialog -> {
                _dialogMessageState.value = null
            }
        }
    }

    override suspend fun handleIntentActionsTracker(
        intent: RegisterIntent,
        actions: List<RegisterAction>
    ) {
        //TODO("Not yet implemented")
    }

    private fun clearError(){
        savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
            emailError = null,
            passwordError = null,
            confirmPasswordError = null
        )
        _dialogMessageState.value = null
    }

    private fun register(){
        //show loading
        savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
            isLoading = true,
        )

        //validate email
        val emailError = validateBasicInformationUseCase.validateEmail(registerUiState.value.emailValue)
        emailError?.let {
            savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                emailError = it,
            )
            return
        }

        //validate password
        val passwordError = validateBasicInformationUseCase.validatePassword(registerUiState.value.passwordValue)
        passwordError?.let {
            savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                passwordError = it,
            )
            return
        }

        //validate confirm password
        val confirmPasswordError = validateBasicInformationUseCase.validatePassword(registerUiState.value.confirmPasswordValue)
        confirmPasswordError?.let {
            savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                confirmPasswordError = it,
            )
            return
        }

        val twoPasswordError = validateBasicInformationUseCase.samePassword(
            password =  registerUiState.value.passwordValue,
            confirmPassword = registerUiState.value.confirmPasswordValue
        )
        twoPasswordError?.let {
            savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                confirmPasswordError = it,
            )
            return
        }

        //register with server / firebase
        viewModelScope.launch {
            when (val result = registerUseCase.invoke(email = registerUiState.value.emailValue, password = registerUiState.value.passwordValue)) {
                is UseCaseResult.Failure -> {
                    savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                        isLoading = false
                    )
                    _dialogMessageState.value = "Failure"
                }
                is UseCaseResult.Success -> {
                    savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                        isLoading = false,
                    )
                    _goToMainActivityStateFlow.value = true
                }
                is UseCaseResult.CustomerError -> {
                    savedStateHandle[REGISTER_UI_STATE_KEY] = registerUiState.value.copy(
                        isLoading = false,
                    )
                    _dialogMessageState.value = result.error
                }
            }
        }
    }
}