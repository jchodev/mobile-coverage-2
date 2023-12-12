package com.jerry.assessment.features.mobilecoverage.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jerry.assessment.R
import com.jerry.assessment.designllib.common.bottomBorder
import com.jerry.assessment.features.mobilecoverage.domain.model.ProviderSignalType
import com.jerry.assessment.features.mobilecoverage.domain.model.SignalType
import com.jerry.assessment.designllib.theme.*

@Composable
fun MobileCoverageTable(
    providerSignalType : ProviderSignalType
) {
    Table(providerSignalType = providerSignalType)
}

@Composable
private fun Table(
    providerSignalType : ProviderSignalType
) {
    val title = when (providerSignalType.signalType){
        SignalType.DATA_INDOOR -> stringResource(id = R.string.signal_type_data_indoor)
        SignalType.DATA_OUTDOOR -> stringResource(id = R.string.signal_type_data_outdoor)
        SignalType.VOICE_INDOOR -> stringResource(id = R.string.signal_type_invoice_indoor)
        SignalType.VOICE_OUTDOOR -> stringResource(id = R.string.signal_type_invoice_outdoor)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        //title
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = Typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        //table header
        Row(
            modifier = Modifier
                .fillMaxSize()
                .bottomBorder(1.dp, MaterialTheme.colorScheme.onBackground)
        ) {
            TextCell(stringResource(id = R.string.mobile_provider),  Modifier.weight(1f))
            TextCell(stringResource(id = R.string.mobile_4g), Modifier.weight(1f))
            TextCell(stringResource(id = R.string.mobile_no_4g),  Modifier.weight(1f))
        }

        //table content
        providerSignalType.providerSignalList.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextCell(item.provider.toString(), Modifier.weight(1f))
                IconCell(item.fourG,  Modifier.weight(1f))
                IconCell(item.nonFourG, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun TextCell(text: String,modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxHeight()
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun IconCell(value: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        MobileSignalIconBySignalValue(value = value)
    }
}
