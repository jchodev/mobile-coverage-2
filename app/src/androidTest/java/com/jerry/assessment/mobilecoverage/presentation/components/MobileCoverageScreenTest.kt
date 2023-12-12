package com.jerry.assessment.features.mobilecoverage.presentation.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.jerry.assessment.features.mobilecoverage.MobileCoverageTestStubs
import com.jerry.assessment.R
import com.jerry.assessment.designllib.dialog.HmAlertDialog
import com.jerry.assessment.designllib.loading.HmLoading
import com.jerry.assessment.designllib.theme.AssessmentTheme
import com.jerry.assessment.features.mobilecoverage.data.mapper.toMobileAvailability
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability
import org.junit.Rule
import org.junit.Test

class MobileCoverageScreenTest {

    private val ERROR_MSG = "testError"

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testWithSuccessCase() {
        val testMobileAvailability =
            MobileCoverageTestStubs.testMobileAvailabilityDto.toMobileAvailability()
        val context: Context = InstrumentationRegistry.getInstrumentation().getTargetContext()

        val selectedMobileAvailabilityState = mutableStateOf<MobileAvailability?>(null)
        val mobileAvailabilitiesState = mutableStateOf<List<MobileAvailability>?>(null)
        val loadingState = mutableStateOf<Boolean?>(null)
        val errorState = mutableStateOf<Any?>(null)

        rule.setContent {
            AssessmentTheme {
                MobileCoverageScreen(
                    modifier = Modifier,
                    onSearchBtnClick = { },
                    onPostCodeValueChange = { },
                    loadingState = loadingState,
                    errorState = errorState,
                    retryDialog = {
                        retryDialogCompose(it)
                    },
                    loadingCompose = loadCompose,
                    mobileAvailabilitiesState = mobileAvailabilitiesState,
                    selectedMobileAvailabilityState = selectedMobileAvailabilityState,
                    onAddressSelected = { }
                )
            }
        }

        // assign
        rule.runOnIdle {
            selectedMobileAvailabilityState.value = testMobileAvailability
            mobileAvailabilitiesState.value = listOf(testMobileAvailability)
        }

        //check
        rule.onNodeWithText(context.resources.getString(R.string.mobile_provider)).assertExists()
        rule.onNodeWithText(context.resources.getString(R.string.mobile_4g)).assertExists()
        rule.onNodeWithText(context.resources.getString(R.string.mobile_no_4g)).assertExists()
    }

    @Test
    fun testWithErrorCase() {
        val selectedMobileAvailabilityState = mutableStateOf<MobileAvailability?>(null)
        val mobileAvailabilitiesState = mutableStateOf<List<MobileAvailability>?>(null)
        val loadingState = mutableStateOf<Boolean?>(null)
        val errorState = mutableStateOf<Any?>(null)

        rule.setContent {
            AssessmentTheme {
                MobileCoverageScreen(
                    modifier = Modifier,
                    onSearchBtnClick = { },
                    onPostCodeValueChange = { },
                    loadingState = loadingState,
                    errorState = errorState,
                    retryDialog = {
                        retryDialogCompose(it)
                    },
                    loadingCompose = loadCompose,
                    mobileAvailabilitiesState = mobileAvailabilitiesState,
                    selectedMobileAvailabilityState = selectedMobileAvailabilityState,
                    onAddressSelected = { }
                )
            }
        }

        // assign
        rule.runOnIdle {
            errorState.value = ERROR_MSG
        }

        //check
        rule.onNodeWithText(ERROR_MSG).assertExists()
    }

    private val loadCompose = @Composable {
        HmLoading()
    }

    private val retryDialogCompose: @Composable (Any) -> Unit =
        @Composable {
            HmAlertDialog(
                title = "",
                message = ERROR_MSG,
                onLeftClick = { } ,
                rightBtnStr = stringResource(id = R.string.retry),
                onRightClick = { }
            )
        }

}