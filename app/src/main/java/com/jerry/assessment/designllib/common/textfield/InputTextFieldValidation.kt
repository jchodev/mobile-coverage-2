package com.jerry.assessment.designllib.common.textfield

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.jerry.assessment.R

import com.jerry.assessment.designllib.theme.AssessmentTheme


//Ref with : https://github.com/mohammadjoumani/validation_jetpack_compose_clean_architecture/blob/main/app/src/main/java/com/mmj/validation/presentation/component/CustomTextFieldApp.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextFieldValidation(
    placeholder: String,
    onValueChange: (String) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    modifier: Modifier = Modifier,
    text: String = "",
    error: String? = null,

    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isVisible: Boolean = false,
) {

//    val isKeyboardTypeNumber = keyboardType == KeyboardType.Phone || keyboardType == KeyboardType.Number
    val context = LocalContext.current
//    val interactionSource = remember { MutableInteractionSource() }
//    val isFocused by interactionSource.collectIsFocusedAsState()
//    val focusRequester = remember {
//        FocusRequester()
//    }

    val isError = error != null

    Column(modifier = modifier) {
        OutlinedTextField(
            singleLine = true,
            value = text ?: "",
            onValueChange = onValueChange,
            label = { Text(text = placeholder) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            isError = isError,
            visualTransformation =
            if (keyboardType == KeyboardType.Password) {
                if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            //interactionSource = interactionSource
        )
        Text(
            text = error ?: "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

//https://developer.android.com/jetpack/compose/tooling/previews
@SuppressLint("UnrememberedMutableState")
@Preview(
    showSystemUi = true,
    name = "Normal Mode"
)
@Composable
private fun PreviewInputTextFieldValidationNormalModel(){

    AssessmentTheme {
        InputTextFieldValidation(
            placeholder = "this is test",
            text = ""
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showSystemUi = true,
    name = "Default Text Mode"
)
@Composable
private fun PreviewInputTextFieldValidationDefaultText(){
    AssessmentTheme {
        InputTextFieldValidation(
            placeholder = "this is test",
            text = "this is default text"
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showSystemUi = true,
    name = "Error Mode"
)
@Composable
private fun PreviewInputTextFieldValidationError(){
    AssessmentTheme {
        InputTextFieldValidation(
            placeholder = "this is test",
            text = "this is default text",
            error = "this is error"
        )
    }
}