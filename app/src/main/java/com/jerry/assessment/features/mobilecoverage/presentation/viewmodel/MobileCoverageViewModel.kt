package com.jerry.assessment.features.mobilecoverage.presentation.viewmodel


import androidx.lifecycle.viewModelScope
import com.jerry.assessment.base.presentation.mvi.BaseViewModel
import com.jerry.assessment.base.usecase.UseCaseResult
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability
import com.jerry.assessment.features.mobilecoverage.domain.usecase.GetMobileCoverUseCase
import com.jerry.assessment.features.mobilecoverage.presentation.mvi.MobileCoverageAction
import com.jerry.assessment.features.mobilecoverage.presentation.mvi.MobileCoverageIntent

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class MobileCoverageViewModel @Inject constructor(
    private val getMobileCoverUseCase: GetMobileCoverUseCase
): BaseViewModel<MobileCoverageIntent, MobileCoverageAction>() {

    //---- PostCode
    private val _postCodeState = MutableStateFlow<String?>(null)
    val postCodeState = _postCodeState.asStateFlow()

    //---- dismissKeyboard
    private val _dismissKeyboardState = MutableStateFlow<Boolean>(false)
    val dismissKeyboardState = _dismissKeyboardState.asStateFlow()

    //---- Loading
    private val _loadingState = MutableStateFlow(false)
    val loadingState =_loadingState.asStateFlow()

    //----- Error
    private val _errorState = MutableStateFlow<Any?>(null)
    val errorState = _errorState.asStateFlow()

    //----- List of MobileAvailability
    private val _mobileAvailabilitiesState = MutableStateFlow<List<MobileAvailability>?>(null)
    val mobileAvailabilitiesState = _mobileAvailabilitiesState.asStateFlow()

    //----- Selected MobileAvailability (from drop down list)
    private val _selectedMobileAvailabilityState = MutableStateFlow<MobileAvailability?>(null)
    val selectedMobileAvailabilityState= _selectedMobileAvailabilityState.asStateFlow()

    private fun getMobileSignalStrengthByPostCode(postCode : String?){
        if (postCode.isNullOrEmpty()){
            return
        }

        viewModelScope.launch {
            _loadingState.emit(true)

            when (val result = getMobileCoverUseCase.invoke(postCode = postCode)) {
                is UseCaseResult.Failure -> {
                    _errorState.emit(result.throwable)
                    _loadingState.emit(false)
                }
                is UseCaseResult.Success -> {
                    _mobileAvailabilitiesState.emit(result.data)
                    _selectedMobileAvailabilityState.emit(result.data[0])
                    _loadingState.emit(false)
                }
                is UseCaseResult.CustomerError -> {
                    _errorState.emit(result.error)
                    _loadingState.emit(false)
                }
            }
        }
    }


    override suspend fun handleIntent(intent: MobileCoverageIntent): List<MobileCoverageAction> {
        return when (intent){
            is MobileCoverageIntent.SearchButtonClick -> listOf(
                MobileCoverageAction.DismissKeyboard,
                MobileCoverageAction.GetMobileCoverageByPostCode(postCodeState.value)
            )
            is MobileCoverageIntent.RetryClick ->  {
                listOf(
                    MobileCoverageAction.DismissDialog,
                    MobileCoverageAction.GetMobileCoverageByPostCode(postCodeState.value)
                )
            }
            is MobileCoverageIntent.PostCodeValueChange -> listOf(MobileCoverageAction.PostCodeValueChange(intent.postCode))
            is MobileCoverageIntent.DismissDialog -> listOf(
                MobileCoverageAction.DismissDialog,
            )
            is MobileCoverageIntent.NewAddressChange -> listOf(MobileCoverageAction.SetSelectedMobileAvailability(intent.newMobileAvailability))
        }
    }

    override suspend fun handleAction(action: MobileCoverageAction) {
        when (action){
            is MobileCoverageAction.GetMobileCoverageByPostCode -> {
                getMobileSignalStrengthByPostCode(action.postCode)
            }
            is MobileCoverageAction.PostCodeValueChange -> _postCodeState.emit(action.postCode)
            is MobileCoverageAction.DismissDialog -> {
                _errorState.emit(null)
            }
            is MobileCoverageAction.SetSelectedMobileAvailability -> _selectedMobileAvailabilityState.emit(action.value)
            is MobileCoverageAction.DismissKeyboard -> {
                _dismissKeyboardState.emit( !dismissKeyboardState.value )
            }
        }
    }

    override suspend fun handleIntentActionsTracker(intent: MobileCoverageIntent, actions: List<MobileCoverageAction>){
        //TODO("Not yet implemented")
    }
}