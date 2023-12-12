package com.jerry.assessment.features.mobilecoverage.di

import com.jerry.assessment.features.mobilecoverage.data.remote.api.MobileCoverageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MobileCoverageModule {

    @Provides
    @Singleton
    fun provideMobileCoverageApi(retrofit: Retrofit): MobileCoverageApi {
        return retrofit.create(MobileCoverageApi::class.java)
    }

}