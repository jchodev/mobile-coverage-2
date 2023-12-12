package com.jerry.assessment.features.loginreg.di

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jerry.assessment.features.loginreg.data.repository.AuthRepositoryImpl
import com.jerry.assessment.features.loginreg.domain.repository.AuthRepository
import com.jerry.assessment.features.loginreg.presentation.provider.LocalizedProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LoginRegModule {

    @Provides
    @Singleton
    fun provideLocalizedProvider(app: Application): LocalizedProvider {
        return LocalizedProvider(context = app)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(
            auth = auth,
            firestore = firebaseFirestore
        )
    }
}