package com.jerry.assessment.features.mobilecoverage.data.remote.response

import com.jerry.assessment.features.mobilecoverage.data.remote.dto.MobileAvailabilityDto
import com.squareup.moshi.Json

data class MobileCoverageSuccessResponse (
    @Json(name = "PostCode") val postCode: String?,
    @Json(name = "Availability") val availabilities :  List<MobileAvailabilityDto>?
)