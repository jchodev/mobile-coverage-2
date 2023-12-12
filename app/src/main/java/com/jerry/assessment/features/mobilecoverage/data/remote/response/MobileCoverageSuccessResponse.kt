package com.jerry.assessment.features.mobilecoverage.data.remote.response

import com.jerry.assessment.features.mobilecoverage.data.remote.dto.MobileAvailabilityDto
import com.squareup.moshi.Json

data class MobileCoverageSuccessResponse (
    @field:Json(name = "PostCode") val postCode: String?,
    @field:Json(name = "Availability") val availabilities :  List<MobileAvailabilityDto>?
)