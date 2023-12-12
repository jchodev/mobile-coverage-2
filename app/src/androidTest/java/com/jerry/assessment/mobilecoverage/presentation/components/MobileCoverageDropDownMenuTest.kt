package com.jerry.assessment.features.mobilecoverage.presentation.components


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.jerry.assessment.features.mobilecoverage.MobileCoverageTestStubs
import com.jerry.assessment.designllib.theme.AssessmentTheme

import com.jerry.assessment.features.mobilecoverage.data.mapper.toMobileAvailability

import org.junit.Rule
import org.junit.Test



class MobileCoverageDropDownMenuTest {

    @get:Rule
    val rule = createComposeRule()


    @Test
    fun testWithExpected() {

        val testMobileAvailability = MobileCoverageTestStubs.testMobileAvailabilityDto.toMobileAvailability()

        //assign
        rule.setContent {
            AssessmentTheme {
                MobileCoverageDropDownMenu(
                    addresses = listOf(
                        testMobileAvailability
                    ),
                    onAddressSelected = { },
                )
            }
        }

        //check
        rule.onNodeWithText(testMobileAvailability.addressShortDescription).assertExists()
    }
}

