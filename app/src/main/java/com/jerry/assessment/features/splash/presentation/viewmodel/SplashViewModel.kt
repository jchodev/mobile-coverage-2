package com.jerry.assessment.features.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): ViewModel() {

    private val _isCheckedWithFirebase = MutableStateFlow<Boolean?>( null)
    val isCheckedWithFirebase = _isCheckedWithFirebase.asStateFlow()


    fun checkLogin(){
        viewModelScope.launch {
            _isCheckedWithFirebase.value = firebaseAuth.currentUser != null
        }
    }
}