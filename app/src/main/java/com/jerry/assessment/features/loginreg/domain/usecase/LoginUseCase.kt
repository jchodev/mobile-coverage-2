package com.jerry.assessment.features.loginreg.domain.usecase


import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.jerry.assessment.base.usecase.UseCaseResult
import com.jerry.assessment.base.util.Resource
import com.jerry.assessment.domain.manager.DataStoreManager
import com.jerry.assessment.features.loginreg.domain.repository.AuthRepository
import com.jerry.assessment.features.loginreg.presentation.provider.LocalizedProvider
import kotlinx.coroutines.flow.first

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val authRepository: AuthRepository,
    private val localizedProvider: LocalizedProvider
) {
    suspend fun invoke(email: String, password: String): UseCaseResult<String> {
        val result = authRepository.login(
            email = email,
            password = password,
            documentId = dataStoreManager.getUserDocumentId().first() ?: ""
        )
        return if (result.isSuccess) {
            if (result.getOrNull() != null) {
                UseCaseResult.Success(result.getOrNull())
            }
            UseCaseResult.CustomerError(localizedProvider.getLoginFailed())
        } else {
            when (val exception = result.exceptionOrNull()) {
                is FirebaseAuthException -> {
                    UseCaseResult.CustomerError(exception.errorCode)
                }

                is FirebaseException -> {
                    //https://cloud.google.com/identity-platform/docs/admin/email-enumeration-protection
                    //Email should be a valid google account
                    val message = exception.message
                    if (message?.contains("INVALID_LOGIN_CREDENTIALS") == true) {
                        UseCaseResult.CustomerError(localizedProvider.getInvalidGoogleEmail())
                    }
                    else {
                        UseCaseResult.CustomerError(localizedProvider.getLoginFailed())
                    }
                }
                else ->{
                    UseCaseResult.CustomerError(exception?.message ?:  localizedProvider.getLoginFailed())
                }
            }
        }
    }

    suspend fun forgetPassword(email: String): UseCaseResult<String> {
        val result = authRepository.forgetPassword(email = email)
        return if (result.isSuccess) {
            UseCaseResult.Success(localizedProvider.getEmailSent())
        } else {
            when (val exception = result.exceptionOrNull()){
                is FirebaseAuthException -> {
                    UseCaseResult.CustomerError(exception.errorCode)
                }
                is FirebaseException -> {
                    //https://cloud.google.com/identity-platform/docs/admin/email-enumeration-protection
                    //Email should be a valid google account
                    val message  = exception.message
                    if (message?.contains("INVALID_LOGIN_CREDENTIALS") == true){
                        UseCaseResult.CustomerError(localizedProvider.getInvalidGoogleEmail())
                    }
                    else {
                        UseCaseResult.CustomerError(message ?: localizedProvider.getUnknownError())
                    }
                }
                else ->{
                    UseCaseResult.CustomerError(exception?.message ?:  localizedProvider.getUnknownError())
                }
            }
        }

    }
}
