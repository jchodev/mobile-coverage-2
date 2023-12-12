package com.jerry.assessment.features.mobilecoverage.presentation.mvi


import com.jerry.assessment.base.presentation.mvi.MviAction
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability

sealed class MobileCoverageAction: MviAction {
    class GetMobileCoverageByPostCode(val postCode: String?): MobileCoverageAction()
    class PostCodeValueChange(val postCode: String): MobileCoverageAction()
    class SetSelectedMobileAvailability(val value: MobileAvailability): MobileCoverageAction()
    object DismissDialog: MobileCoverageAction()
    object DismissKeyboard: MobileCoverageAction()
}