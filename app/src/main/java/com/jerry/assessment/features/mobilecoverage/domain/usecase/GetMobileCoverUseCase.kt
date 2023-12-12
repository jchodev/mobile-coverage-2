package com.jerry.assessment.features.mobilecoverage.domain.usecase

import com.jerry.assessment.base.usecase.UseCaseResult
import com.jerry.assessment.base.util.Resource
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability
import com.jerry.assessment.features.mobilecoverage.domain.repository.MobileCoverageRepository
import javax.inject.Inject

class GetMobileCoverUseCase @Inject constructor(
    private val mobileCoverageRepository: MobileCoverageRepository
) {
    suspend fun invoke(postCode: String): UseCaseResult<List<MobileAvailability>> {
        return try {
            val response = mobileCoverageRepository.getMobileCoverageByPostCode2(
                postCode = postCode.trim()
            )
            when (response) {
                is Resource.Error -> {
                    UseCaseResult.CustomerError(response.message!!)
                }
                is Resource.Success -> {
                    UseCaseResult.Success(response.data ?: emptyList())
                }
            }
        } catch (e: Exception){
            UseCaseResult.Failure(e)
        }
    }
}