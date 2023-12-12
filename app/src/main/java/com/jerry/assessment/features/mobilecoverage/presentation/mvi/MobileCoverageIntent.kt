package com.jerry.assessment.features.mobilecoverage.presentation.mvi

import com.jerry.assessment.base.presentation.mvi.MviIntent
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability


sealed class MobileCoverageIntent: MviIntent {
    object SearchButtonClick: MobileCoverageIntent()
    object RetryClick: MobileCoverageIntent()
    object DismissDialog: MobileCoverageIntent()
    class PostCodeValueChange(val postCode: String): MobileCoverageIntent()
    class NewAddressChange(val newMobileAvailability: MobileAvailability): MobileCoverageIntent()
}
