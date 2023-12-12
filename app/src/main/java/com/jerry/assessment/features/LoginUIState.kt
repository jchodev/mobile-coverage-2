package com.jerry.assessment.features

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUIState (
    val emailValue: String = "",
    val emailError: String? = null,
    val passwordValue: String = "",
    val passwordError: String? = null,
    val isVisiblePassword: Boolean = false,

    val isLoading: Boolean = false
) : Parcelable