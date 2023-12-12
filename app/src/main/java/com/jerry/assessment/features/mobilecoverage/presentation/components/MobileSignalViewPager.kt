package com.jerry.assessment.features.mobilecoverage.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MobileSignalViewPager(
    mobileAvailabilityState: State<MobileAvailability?>
) {
    mobileAvailabilityState.value?.let {
        val mobileAvailability = it
        val pageCount = mobileAvailability.signalTypeList.size
        val pagerState = rememberPagerState { pageCount }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalPager(state = pagerState) {
                MobileCoverageTable(mobileAvailability.signalTypeList[it])
            }

            Spacer(modifier = Modifier.padding(4.dp))

            DotsIndicator(
                totalDots = pageCount,
                selectedIndex = pagerState.currentPage,
                selectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unSelectedColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun DotsIndicator(
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
){

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()

        ) {
            items(totalDots) { index ->
                if (index == selectedIndex) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(selectedColor)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(unSelectedColor)
                    )
                }

                if (index != totalDots - 1) {
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                }
            }
        }
    }

}