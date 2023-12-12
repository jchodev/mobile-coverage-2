package com.jerry.assessment.features.mobilecoverage.domain.repository

import com.jerry.assessment.base.util.Resource
import com.jerry.assessment.features.mobilecoverage.data.remote.response.MobileCoverageSuccessResponse
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability

interface MobileCoverageRepository {

    suspend fun getMobileCoverageByPostCode2(postCode: String): Resource<List<MobileAvailability>>
    suspend fun getMobileCoverageByPostCode(postCode: String): Resource<MobileCoverageSuccessResponse>

}