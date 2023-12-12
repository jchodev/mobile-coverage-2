package com.jerry.assessment.features

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterUIState (
    val emailValue: String = "",
    val emailError: String? = null,
    val passwordValue: String = "",
    val passwordError: String? = null,
    val isVisiblePassword: Boolean = false,

    val confirmPasswordValue: String = "",
    val confirmPasswordError: String? = null,
    val isVisibleConfirmPassword: Boolean = false,

    val isLoading: Boolean = false
) : Parcelable