package com.jerry.assessment.features.loginreg.presentation.provider

import android.content.Context
import com.jerry.assessment.R
import javax.inject.Inject

class LocalizedProvider @Inject constructor(
    private val context: Context,
) {
    private val stringResources = context.resources

    fun getInvalidEmail(): String {
        return stringResources.getString(R.string.invalid_email)
    }

    fun getInvalidGoogleEmail(): String {
        return stringResources.getString(R.string.invalid_email)
    }

    fun getCannotBlankErrorMessage(): String {
        return stringResources.getString(R.string.cannot_blank)
    }

    fun getInvalidEmailErrorMessage(): String {
        return stringResources.getString(R.string.invalid_email)
    }

    fun getPasswordTooShortErrorMessage(): String {
        return stringResources.getString(R.string.password_too_short)
    }

    fun getTwoPasswordNotEqual(): String {
        return stringResources.getString(R.string.two_password_not_equal)
    }

    fun getLoginFailed(): String {
        return stringResources.getString(R.string.login_failed)
    }

    fun getRegistrationFailed(): String {
        return stringResources.getString(R.string.registration_failed)
    }

    fun getNoInternetConnection(): String {
        return stringResources.getString(R.string.no_internet_connection)
    }

    fun getEmailIsAlready(): String {
        return stringResources.getString(R.string.no_internet_connection)
    }

    fun getInvalidPassword(): String {
        return stringResources.getString(R.string.invalid_password)
    }

    fun getUserNotFound(): String {
        return stringResources.getString(R.string.user_not_found)
    }

    fun getTooManyRequests(): String {
        return stringResources.getString(R.string.too_many_requests)
    }

    fun getEmailSent(): String {
        return stringResources.getString(R.string.email_sent)
    }

    fun getUnknownError(): String {
        return stringResources.getString(R.string.unknown_error)
    }
}