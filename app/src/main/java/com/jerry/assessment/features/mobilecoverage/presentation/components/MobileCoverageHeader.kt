package com.jerry.assessment.features.mobilecoverage.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization

import com.jerry.assessment.designllib.space.HmDefaultVerticalSpace
import com.jerry.assessment.R


/*
    Page will like:
    Please enter your postcode below to view the predicted mobile availability in your area.
    EditText
    [Button]
 */
@Composable
fun MobileCoverageHeader(
    onSearchBtnClick: () -> Unit,
    onPostCodeValueChange: (String) -> Unit
) {
    var postCode by remember { mutableStateOf("") }


    Column {
        Text (
            text = stringResource(id = R.string.mobile_cover_page_title)
        )

        HmDefaultVerticalSpace()

        OutlinedTextField(
            value = postCode,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(id = R.string.mobile_cover_enter_post_code)) },
            placeholder = { Text(stringResource(id = R.string.mobile_cover_enter_post_code)) },
            onValueChange = {
                postCode = it
                onPostCodeValueChange(it)
            }
        )

        HmDefaultVerticalSpace()

        Button(
            onClick = {
                onSearchBtnClick()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.mobile_cover_check_signal_strength))
        }

        HmDefaultVerticalSpace()
    }
}

