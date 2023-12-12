package com.jerry.assessment.features.mobilecoverage.data.mapper


import com.jerry.assessment.features.mobilecoverage.MobileCoverageTestStubs
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@MockKExtension.ConfirmVerification
class MobileCoverageMapperTest {

    @Test
    fun `test MobileAvailabilityDto toMobileAvailability() function with expected result`()  {
        val testDto = MobileCoverageTestStubs.testMobileAvailabilityDto

        //action
        val actual = testDto.toMobileAvailability()

        //verify
        Assertions.assertEquals(
            testDto.postCode, actual.postCode
        )
        Assertions.assertEquals(
            testDto.addressShortDescription, actual.addressShortDescription
        )
        Assertions.assertEquals(
            testDto.postCode, actual.postCode
        )

        Assertions.assertEquals(testDto.eeVoiceOutdoor, actual.eeVoiceOutdoor)
        Assertions.assertEquals(testDto.eeVoiceOutdoorNo4g, actual.eeVoiceOutdoorNo4g)
        Assertions.assertEquals(testDto.eeVoiceIndoor, actual.eeVoiceIndoor)
        Assertions.assertEquals(testDto.eeVoiceIndoorNo4g, actual.eeVoiceIndoorNo4g)
        Assertions.assertEquals(testDto.eeDataOutdoor, actual.eeDataOutdoor)
        Assertions.assertEquals(testDto.eeDataOutdoorNo4g, actual.eeDataOutdoorNo4g)
        Assertions.assertEquals(testDto.eeDataIndoor, actual.eeDataIndoor)
        Assertions.assertEquals(testDto.eeDataIndoorNo4g, actual.eeDataIndoorNo4g)

        Assertions.assertEquals(testDto.h3VoiceOutdoor, actual.h3VoiceOutdoor)
        Assertions.assertEquals(testDto.h3VoiceOutdoorNo4g, actual.h3VoiceOutdoorNo4g)
        Assertions.assertEquals(testDto.h3VoiceIndoor, actual.h3VoiceIndoor)
        Assertions.assertEquals(testDto.h3VoiceIndoorNo4g, actual.h3VoiceIndoorNo4g)
        Assertions.assertEquals(testDto.h3DataOutdoor, actual.h3DataOutdoor)
        Assertions.assertEquals(testDto.h3DataOutdoorNo4g, actual.h3DataOutdoorNo4g)
        Assertions.assertEquals(testDto.h3DataIndoor, actual.h3DataIndoor)
        Assertions.assertEquals(testDto.h3DataIndoorNo4g, actual.h3DataIndoorNo4g)

        Assertions.assertEquals(testDto.o2VoiceOutdoor, actual.o2VoiceOutdoor)
        Assertions.assertEquals(testDto.o2VoiceOutdoorNo4g, actual.o2VoiceOutdoorNo4g)
        Assertions.assertEquals(testDto.o2VoiceIndoor, actual.o2VoiceIndoor)
        Assertions.assertEquals(testDto.o2VoiceIndoorNo4g, actual.o2VoiceIndoorNo4g)
        Assertions.assertEquals(testDto.o2DataOutdoor, actual.o2DataOutdoor)
        Assertions.assertEquals(testDto.o2DataOutdoorNo4g, actual.o2DataOutdoorNo4g)
        Assertions.assertEquals(testDto.o2DataIndoor, actual.o2DataIndoor)
        Assertions.assertEquals(testDto.o2DataIndoorNo4g, actual.o2DataIndoorNo4g)

        Assertions.assertEquals(testDto.voVoiceOutdoor, actual.voVoiceOutdoor)
        Assertions.assertEquals(testDto.voVoiceOutdoorNo4g, actual.voVoiceOutdoorNo4g)
        Assertions.assertEquals(testDto.voVoiceIndoor, actual.voVoiceIndoor)
        Assertions.assertEquals(testDto.voVoiceIndoorNo4g, actual.voVoiceIndoorNo4g)
        Assertions.assertEquals(testDto.voDataOutdoor, actual.voDataOutdoor)
        Assertions.assertEquals(testDto.voDataOutdoorNo4g, actual.voDataOutdoorNo4g)
        Assertions.assertEquals(testDto.voDataIndoor, actual.voDataIndoor)
        Assertions.assertEquals(testDto.voDataIndoorNo4g, actual.voDataIndoorNo4g)
    }
}