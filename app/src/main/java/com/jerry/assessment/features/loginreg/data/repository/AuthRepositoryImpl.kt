package com.jerry.assessment.features.loginreg.data.repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.jerry.assessment.common.utils.MoshiParser
import com.jerry.assessment.features.loginreg.data.mapper.toFirestoreUserData
import com.jerry.assessment.features.loginreg.domain.model.FirestoreUserData

import com.jerry.assessment.features.loginreg.domain.repository.AuthRepository


import kotlinx.coroutines.tasks.await
import timber.log.Timber


import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): AuthRepository {

    override suspend fun login(
        email: String,
        password: String,
        documentId: String,
    ): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Timber.d("AuthRepositoryImpl::login::result:${result}")
            val user = result.user
            if (user != null){
                Result.success(user)
            } else {
                Result.failure(NullPointerException())
            }
        } catch (e: Exception){
            Timber.d("AuthRepositoryImpl::login::Exception:${e}")
            Result.failure(e)
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Timber.d("AuthRepositoryImpl::register::result:${result}")
            val user = result.user

            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(NullPointerException())
            }
        } catch (e: Exception){
            Timber.d("AuthRepositoryImpl::register::Exception:${e}")
            Result.failure(e)
        }

    }

    override suspend fun forgetPassword(email: String): Result<String?> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.success(null)
        } catch ( e: Exception){
            Result.failure(e)
        }

    }

}