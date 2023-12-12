package com.jerry.assessment.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jerry.assessment.domain.manager.DataStoreManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStoreManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        "assessment_datastore"
    )

    override suspend fun saveUserDocumentId(documentId: String) {
        context.dataStore.edit {
            it[PreferenceKeys.DOCUMENT_ID] = documentId
        }
    }

    override fun getUserDocumentId(): Flow<String?> {
        return context.dataStore.data
            .map {
                it[PreferenceKeys.DOCUMENT_ID]
            }
    }


    private object PreferenceKeys {
        val DOCUMENT_ID = stringPreferencesKey("KEY_DOCUMENT_ID")
    }
}