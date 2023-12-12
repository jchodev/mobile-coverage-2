package com.jerry.assessment.features.loginreg.domain.repository

import com.google.firebase.auth.FirebaseUser


interface AuthRepository {

    suspend fun login(email: String, password: String, documentId: String): Result<FirebaseUser>
    suspend fun register(email: String, password: String): Result<FirebaseUser>

    suspend fun forgetPassword(email: String): Result<String?>
}