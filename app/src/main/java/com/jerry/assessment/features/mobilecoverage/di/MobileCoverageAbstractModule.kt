package com.jerry.assessment.features.mobilecoverage.di

import com.jerry.assessment.features.mobilecoverage.data.repository.MobileCoverageRepositoryImpl
import com.jerry.assessment.features.mobilecoverage.domain.repository.MobileCoverageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MobileCoverageAbstractModule {

    @Binds
    @Singleton
    abstract fun bindMobileCoverageRepository(mobileCoverageRepository: MobileCoverageRepositoryImpl): MobileCoverageRepository

}