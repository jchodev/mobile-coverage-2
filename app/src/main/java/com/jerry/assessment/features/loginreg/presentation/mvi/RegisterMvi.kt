package com.jerry.assessment.features.loginreg.presentation.mvi

import com.jerry.assessment.base.presentation.mvi.MviAction
import com.jerry.assessment.base.presentation.mvi.MviIntent


sealed class RegisterIntent: MviIntent {
    class EmailChanged(val email: String): RegisterIntent()
    class PasswordChanged(val password: String): RegisterIntent()
    class VisiblePassword(val isVisiblePassword: Boolean) : RegisterIntent()

    class ConfirmPasswordChanged(val password: String): RegisterIntent()
    class VisibleConfirmPassword(val isVisiblePassword: Boolean) : RegisterIntent()

    object Register: RegisterIntent()
    object DismissDialog: RegisterIntent()
}

sealed class RegisterAction: MviAction {
    class AssignEmailValue(val email: String): RegisterAction()
    class AssignPasswordValue(val password: String): RegisterAction()
    class AssignConfirmPasswordValue(val confirmPassword: String): RegisterAction()

    object Register : RegisterAction()
    class VisiblePassword(val visiblePassword: Boolean) : RegisterAction()
    class VisibleConfirmPassword(val visibleConfirmPassword: Boolean) : RegisterAction()

    object DismissDialog: RegisterAction()

//    object GoToForgotPassword: LoginAction()
//    object GoToCreateAccount: LoginAction()

}
