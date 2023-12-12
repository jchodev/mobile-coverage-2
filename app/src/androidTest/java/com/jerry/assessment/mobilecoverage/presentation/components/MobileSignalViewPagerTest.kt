package com.jerry.assessment.features.mobilecoverage.presentation.components

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.jerry.assessment.features.mobilecoverage.MobileCoverageTestStubs
import com.jerry.assessment.R
import com.jerry.assessment.designllib.theme.AssessmentTheme
import com.jerry.assessment.features.mobilecoverage.data.mapper.toMobileAvailability
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability
import org.junit.Rule
import org.junit.Test

class MobileSignalViewPagerTest {

    @get:Rule
    val rule = createComposeRule()


    @Test
    fun testWithExpected() {
        val testMobileAvailability = MobileCoverageTestStubs.testMobileAvailabilityDto.toMobileAvailability()
        val context: Context = InstrumentationRegistry.getInstrumentation().getTargetContext()
        val mobileAvailabilityState = mutableStateOf<MobileAvailability?>(null)

        rule.setContent {
            AssessmentTheme {
                MobileSignalViewPager(
                    mobileAvailabilityState = mobileAvailabilityState
                )
            }
        }

        // assign
        rule.runOnIdle {
            mobileAvailabilityState.value = testMobileAvailability
        }

        //check
        rule.onNodeWithText(context.resources.getString(R.string.mobile_provider)).assertExists()
        rule.onNodeWithText(context.resources.getString(R.string.mobile_4g)).assertExists()
        rule.onNodeWithText(context.resources.getString(R.string.mobile_no_4g)).assertExists()
    }

}