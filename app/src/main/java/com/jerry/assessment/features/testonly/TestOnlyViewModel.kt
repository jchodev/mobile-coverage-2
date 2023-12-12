package com.jerry.assessment.features.testonly

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//https://medium.com/@jerry.cho.dev/viewmodel-with-savedstatehandle-55eb8a96be1d
@HiltViewModel
class TestOnlyViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    /* TODO it should be test with Jetpack UI test !*/
    //it is simple
//    var name by mutableStateOf("")
//        private set

    //using state Flow
    private val _nameState = MutableStateFlow<String>("")
    val nameState = _nameState.asStateFlow()

    val nameWithSaveState = savedStateHandle.getStateFlow("name", "")

    val namePlus = nameWithSaveState.map {
        it.plus("++")
    }

    private val _passcode = MutableStateFlow<String>("")
    val passcode = _passcode.asStateFlow()

//    fun assignName(value: String){
//        name = value
//    }

    fun assignNameState(value: String) {
        _nameState.value = value
    }

    fun assignNameWithSaveState(value: String) {
        savedStateHandle["name"] = value
    }

    fun onPasscodeClick(value: String?) {
        if (value == null) {
            if (passcode.value.isNotEmpty()) {
                _passcode.value = passcode.value.dropLast(1)
            }
        } else {
            _passcode.value = passcode.value + value
        }
    }

}