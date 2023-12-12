package com.jerry.assessment.features.mobilecoverage

import com.jerry.assessment.features.mobilecoverage.data.remote.dto.MobileAvailabilityDto
import com.jerry.assessment.features.mobilecoverage.data.remote.response.MobileCoverageSuccessResponse

class MobileCoverageTestStubs {

    companion object {

        const val testPostCode = "ABC"
        const val testErrorMessage = "this is error"
        const val testAddressShortDescription = "this is address"
        object FakeError: IllegalArgumentException()

        val testMobileAvailabilityDto =  MobileAvailabilityDto(
            uprn = 0,
            addressShortDescription = testAddressShortDescription,
            postCode = testPostCode,
            eeVoiceOutdoor = 1,
            eeVoiceOutdoorNo4g = 1,
            eeVoiceIndoor = 1,
            eeVoiceIndoorNo4g = 1,
            eeDataOutdoor = 1,
            eeDataOutdoorNo4g = 1,
            eeDataIndoor = 1,
            eeDataIndoorNo4g = 1,

            h3VoiceOutdoor = 2,
            h3VoiceOutdoorNo4g = 2,
            h3VoiceIndoor = 2,
            h3VoiceIndoorNo4g = 2,
            h3DataOutdoor = 2,
            h3DataOutdoorNo4g = 2,
            h3DataIndoor = 2,
            h3DataIndoorNo4g = 2,

            o2VoiceOutdoor = 3,
            o2VoiceOutdoorNo4g = 3,
            o2VoiceIndoor = 3,
            o2VoiceIndoorNo4g = 3,
            o2DataOutdoor = 3,
            o2DataOutdoorNo4g = 3,
            o2DataIndoor = 3,
            o2DataIndoorNo4g = 3,

            voVoiceOutdoor = 4,
            voVoiceOutdoorNo4g = 4,
            voVoiceIndoor = 4,
            voVoiceIndoorNo4g = 4,
            voDataOutdoor = 4,
            voDataOutdoorNo4g = 4,
            voDataIndoor = 4,
            voDataIndoorNo4g = 4
        )
        val testMobileCoverageSuccessResponse =
            MobileCoverageSuccessResponse (
                postCode = testPostCode,
                availabilities = listOf(
                    testMobileAvailabilityDto
                )
            )
    }
}