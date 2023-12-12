package com.jerry.assessment.features.loginreg.domain.usecase


import com.jerry.assessment.features.loginreg.presentation.provider.LocalizedProvider
import javax.inject.Inject

class ValidateBasicInformationUseCase @Inject constructor(
    private val localizedProvider: LocalizedProvider
) {

    fun validateEmail(email: String): String? {
        if (email.isBlank()){
            return localizedProvider.getCannotBlankErrorMessage()
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return localizedProvider.getInvalidEmailErrorMessage()
        }

        return null
    }

    fun validatePassword(password: String): String? {
        if (password.isBlank()){
            return localizedProvider.getCannotBlankErrorMessage()
        }
        if (password.length < 8){
            return localizedProvider.getPasswordTooShortErrorMessage()
        }
        return null
    }

    fun samePassword(password: String, confirmPassword: String): String? {
        if (password.compareTo(confirmPassword) != 0){
            return localizedProvider.getTwoPasswordNotEqual()
        }
        return null
    }

}