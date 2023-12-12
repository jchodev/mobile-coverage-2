package com.jerry.assessment.features.loginreg.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.jerry.assessment.features.loginreg.data.mapper.toFirestoreUserData
import com.jerry.assessment.features.loginreg.domain.model.FirestoreUserData
import com.jerry.assessment.features.loginreg.domain.repository.FirestoreRepository
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): FirestoreRepository {

    override suspend fun insertUserData(firebaseUserId: String, email: String): Result<String> {
        return try {
            firebaseFirestore.collection("users")
                .document(firebaseUserId)
                .set(
                    FirestoreUserData(
                        email = email
                    ),
                    SetOptions.merge()
                ).await()
            Result.success(firebaseUserId)
        } catch (e: Exception){
            Timber.d("FirestoreRepositoryImpl::insertUserData::Exception:${e}")
            Result.failure(e)
        }
    }

    override suspend fun getUserDataByDocumentId(documentId: String): Result<FirestoreUserData> {
        return try {
            val docResult = firebaseFirestore
                            .collection("users")
                            .document(documentId)
                            .get()
                            .await()
            val firestoreUserData = docResult.data?.toFirestoreUserData()
            if (firestoreUserData != null)
                Result.success(firestoreUserData)
            else
                Result.failure(NullPointerException())
        } catch (e: Exception){
            Timber.d("AuthRepositoryImpl::login::Exception:${e}")
            Result.failure(e)
        }
    }

}