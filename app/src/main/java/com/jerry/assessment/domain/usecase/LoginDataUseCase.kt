package com.jerry.assessment.domain.usecase

import com.jerry.assessment.domain.manager.DataStoreManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoginDataUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {

    //access token
    suspend fun getUserDocumentId(): String? {
        return dataStoreManager.getUserDocumentId().first()
    }

    suspend fun saveUserDocumentId(documentId: String) {
        return dataStoreManager.saveUserDocumentId(documentId)
    }
}