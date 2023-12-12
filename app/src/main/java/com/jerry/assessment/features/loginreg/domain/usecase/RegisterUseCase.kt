package com.jerry.assessment.features.loginreg.domain.usecase

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.jerry.assessment.base.usecase.UseCaseResult
import com.jerry.assessment.domain.manager.DataStoreManager
import com.jerry.assessment.features.loginreg.domain.repository.AuthRepository
import com.jerry.assessment.features.loginreg.presentation.provider.LocalizedProvider
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val authRepository: AuthRepository,
    private val localizedProvider: LocalizedProvider
) {
    suspend fun invoke(email: String, password: String): UseCaseResult<String> {

        val result = authRepository.register(email = email, password = password)
        return if (result.isSuccess) {
            val documentId = result.getOrNull()
            if (documentId != null) {
                dataStoreManager.saveUserDocumentId(documentId = documentId)
                UseCaseResult.Success(documentId)
            } else {
                UseCaseResult.CustomerError(localizedProvider.getRegistrationFailed())
            }
        } else {

            when (val exception = result.exceptionOrNull()) {
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
                        UseCaseResult.CustomerError(localizedProvider.getLoginFailed())
                    }
                }
                is FirebaseAuthUserCollisionException -> {
                    UseCaseResult.CustomerError(localizedProvider.getEmailIsAlready())
                }
                else ->{
                    UseCaseResult.CustomerError(exception?.message ?:  localizedProvider.getRegistrationFailed())
                }
            }
        }


    }

}

