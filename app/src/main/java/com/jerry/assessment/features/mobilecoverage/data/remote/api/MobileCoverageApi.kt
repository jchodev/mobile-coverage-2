package com.jerry.assessment.features.mobilecoverage.data.remote.api

import com.jerry.assessment.features.mobilecoverage.data.remote.response.MobileCoverageSuccessResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MobileCoverageApi {

    @GET("mobile/coverage/{postCode}")
    suspend fun getMobileCoverageByPostCode(
        @Path("postCode") postCode: String
    ): retrofit2.Response<MobileCoverageSuccessResponse>

}

