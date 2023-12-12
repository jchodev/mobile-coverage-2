package com.jerry.assessment.features.loginreg.presentation.mvi

import com.jerry.assessment.base.presentation.mvi.MviAction
import com.jerry.assessment.base.presentation.mvi.MviIntent
import com.jerry.assessment.features.mobilecoverage.presentation.mvi.MobileCoverageAction


sealed class LoginIntent: MviIntent {
    class EmailChanged(val email: String): LoginIntent()
    class PasswordChanged(val password: String): LoginIntent()
    class VisiblePassword(val isVisiblePassword: Boolean) : LoginIntent()
    object Login: LoginIntent()
    object ForgotPassword: LoginIntent()
    object CreateAccount: LoginIntent()

    object DismissDialog : LoginIntent()
}

sealed class LoginAction: MviAction {
    class AssignEmailValue(val email: String): LoginAction()
    class AssignPasswordValue(val password: String): LoginAction()
    object Login : LoginAction()
    class VisiblePassword(val visiblePassword: Boolean) : LoginAction()

    object GoToForgotPassword: LoginAction()
    object GoToCreateAccount: LoginAction()
    object DismissDialog: LoginAction()
}
