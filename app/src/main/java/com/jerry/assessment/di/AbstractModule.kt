package com.jerry.assessment.di

import com.jerry.assessment.data.local.DataStoreManagerImpl
import com.jerry.assessment.domain.manager.DataStoreManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {

    @Binds
    @Singleton
    abstract fun bindDataStoreManager(dataStoreManager: DataStoreManagerImpl): DataStoreManager

}