package com.jerry.assessment.features.mobilecoverage.presentation.components

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.jerry.assessment.R
import com.jerry.assessment.designllib.theme.AssessmentTheme

import org.junit.Rule
import org.junit.Test

class MobileCoverageHeaderTest {

    @get:Rule
    val rule = createComposeRule()


    @Test
    fun testWithExpected() {

        val context: Context = InstrumentationRegistry.getInstrumentation().getTargetContext()
        val title = context.resources.getString(R.string.mobile_cover_page_title)

        //assign
        rule.setContent {
            AssessmentTheme {
                MobileCoverageHeader(
                    onSearchBtnClick = {},
                    onPostCodeValueChange = {}
                )
            }
        }

        //check
        rule.onNodeWithText(title).assertExists()
    }

}