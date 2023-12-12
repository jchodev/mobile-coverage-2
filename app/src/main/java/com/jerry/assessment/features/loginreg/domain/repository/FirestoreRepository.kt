package com.jerry.assessment.features.loginreg.domain.repository

import com.jerry.assessment.features.loginreg.domain.model.FirestoreUserData

interface FirestoreRepository {

    suspend fun insertUserData(firebaseUserId: String, email: String): Result<String>

    suspend fun getUserDataByDocumentId(documentId: String): Result<FirestoreUserData>

}