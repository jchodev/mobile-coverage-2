package com.jerry.assessment.features.loginreg.domain.repository


interface AuthRepository {

    suspend fun login(email: String, password: String, documentId: String): Result<String?>
    suspend fun register(email: String, password: String): Result<String?>

    suspend fun forgetPassword(email: String): Result<String?>
}