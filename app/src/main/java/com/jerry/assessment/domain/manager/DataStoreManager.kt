package com.jerry.assessment.domain.manager

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {

    suspend fun saveUserDocumentId(documentId: String)
    fun getUserDocumentId(): Flow<String?>

}