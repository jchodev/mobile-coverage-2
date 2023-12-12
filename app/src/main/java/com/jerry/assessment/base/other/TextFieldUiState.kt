package com.jerry.assessment.base.other

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

data class TextFieldUiState(
    val text: String = "",
    var error: TextFieldError? = null,
)

sealed class TextFieldError {
    data class DynamicString(val value: String) : TextFieldError()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : TextFieldError()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}