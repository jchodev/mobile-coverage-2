package com.jerry.assessment.features.mobilecoverage.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jerry.assessment.R
import com.jerry.assessment.designllib.space.HmDefaultVerticalSpace
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability

@Composable
fun MobileCoverageScreen(
    modifier: Modifier,
    onSearchBtnClick: () -> Unit,

    onPostCodeValueChange: (String) -> Unit,
    onAddressSelected: (MobileAvailability) -> Unit,

    mobileAvailabilitiesState: State<List<MobileAvailability>?>,
    selectedMobileAvailabilityState: State<MobileAvailability?>,

    loadingState: State<Boolean?>,
    errorState: State<Any?>,
    retryDialog: @Composable (Any) -> Unit,
    loadingCompose: @Composable () -> Unit
) {
    val listState = rememberLazyListState()
    Box (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MobileCoverageHeader(

                onSearchBtnClick = {
                    onSearchBtnClick()
                },

                onPostCodeValueChange = {
                    onPostCodeValueChange(it)
                }
            )

            LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                if (mobileAvailabilitiesState.value?.isNotEmpty() == true) {

                    item {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.Start),
                            text = stringResource(id = R.string.mobile_cover_select_address_title)
                        )
                    }

                    item {
                        MobileCoverageDropDownMenu(
                            addresses = mobileAvailabilitiesState.value!!,
                            onAddressSelected = onAddressSelected
                        )
                    }

                    item { HmDefaultVerticalSpace() }

                    item {
                        MobileSignalViewPager(
                            selectedMobileAvailabilityState
                        )
                    }

                    item { HmDefaultVerticalSpace() }

                    item {
                        //signal 0
                        Row(Modifier.fillMaxSize()) {
                            MobileSignalIcon0()
                            Text(text = ": ${stringResource(id = R.string.signal_0_desc_content)}")
                        }
                    }

                    item {
                        //signal 1
                        Row(Modifier.fillMaxSize()) {
                            MobileSignalIcon1()
                            Text(text = ": ${stringResource(id = R.string.signal_1_desc_content)}")
                        }
                    }

                    item {
                        //signal 2
                        Row(Modifier.fillMaxSize()) {
                            MobileSignalIcon2()
                            Text(text = ": ${stringResource(id = R.string.signal_2_desc_content)}")
                        }
                    }

                    item {
                        //signal 3
                        Row(Modifier.fillMaxSize()) {
                            MobileSignalIcon3()
                            Text(text = ": ${stringResource(id = R.string.signal_3_desc_content)}")
                        }
                    }

                    item {
                        //signal 4
                        Row(Modifier.fillMaxSize()) {
                            MobileSignalIcon4()
                            Text(text = ": ${stringResource(id = R.string.signal_4_desc_content)}")
                        }
                    }

                    item { HmDefaultVerticalSpace() }
                }
            }

        }

        errorState.value?.let {
            retryDialog(it)
        }

        if (loadingState.value == true) {
            loadingCompose()
        }
    }
}