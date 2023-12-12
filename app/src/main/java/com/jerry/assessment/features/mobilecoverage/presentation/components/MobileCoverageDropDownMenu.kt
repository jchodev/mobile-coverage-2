package com.jerry.assessment.features.mobilecoverage.presentation.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jerry.assessment.R
import com.jerry.assessment.features.mobilecoverage.data.mapper.toMobileAvailability
import com.jerry.assessment.features.mobilecoverage.data.remote.dto.MobileAvailabilityDto

import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileCoverageDropDownMenu(
    addresses: List<MobileAvailability>,
    onAddressSelected: (MobileAvailability) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedAddress by remember(addresses) {
        mutableStateOf(addresses[0])
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            OutlinedTextField(
                value = selectedAddress.addressShortDescription,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded ) },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                addresses.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.addressShortDescription) },
                        onClick = {
                            selectedAddress = item
                            expanded = false
                            onAddressSelected.invoke(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MobileCoverageDropDownMenuPreview() {

    val testMobileAvailabilityDto =  MobileAvailabilityDto(
        uprn = 0,
        addressShortDescription = "this is test",
        postCode = "ABC",
        eeVoiceOutdoor = 1,
        eeVoiceOutdoorNo4g = 1,
        eeVoiceIndoor = 1,
        eeVoiceIndoorNo4g = 1,
        eeDataOutdoor = 1,
        eeDataOutdoorNo4g = 1,
        eeDataIndoor = 1,
        eeDataIndoorNo4g = 1,

        h3VoiceOutdoor = 2,
        h3VoiceOutdoorNo4g = 2,
        h3VoiceIndoor = 2,
        h3VoiceIndoorNo4g = 2,
        h3DataOutdoor = 2,
        h3DataOutdoorNo4g = 2,
        h3DataIndoor = 2,
        h3DataIndoorNo4g = 2,

        o2VoiceOutdoor = 3,
        o2VoiceOutdoorNo4g = 3,
        o2VoiceIndoor = 3,
        o2VoiceIndoorNo4g = 3,
        o2DataOutdoor = 3,
        o2DataOutdoorNo4g = 3,
        o2DataIndoor = 3,
        o2DataIndoorNo4g = 3,

        voVoiceOutdoor = 4,
        voVoiceOutdoorNo4g = 4,
        voVoiceIndoor = 4,
        voVoiceIndoorNo4g = 4,
        voDataOutdoor = 4,
        voDataOutdoorNo4g = 4,
        voDataIndoor = 4,
        voDataIndoorNo4g = 4
    )
    val testMobileAvailability = testMobileAvailabilityDto.toMobileAvailability()

    MobileCoverageDropDownMenu(
        addresses = listOf(
            testMobileAvailability
        ),
        onAddressSelected = { }
    )
}