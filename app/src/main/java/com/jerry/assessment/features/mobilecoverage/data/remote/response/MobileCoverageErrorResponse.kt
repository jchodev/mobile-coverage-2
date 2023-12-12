package com.jerry.assessment.features.mobilecoverage.data.remote.response

import com.squareup.moshi.Json

data class MobileCoverageErrorResponse (
    @field:Json(name = "Error") val error: String?
)