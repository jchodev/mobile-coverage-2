package com.jerry.assessment.features.mobilecoverage.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jerry.assessment.R


private val DEFAULT_SIGNAL_SIZE = 24.dp

@Composable
fun MobileSignalIconBySignalValue(value: Int){
    return when (value) {
        1 -> MobileSignalIcon1()
        2 -> MobileSignalIcon2()
        3 -> MobileSignalIcon3()
        4 -> MobileSignalIcon4()
        else -> {
            MobileSignalIcon0()
        }
    }
}

@Composable
fun MobileSignalIcon0(){
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_signal_0),
        contentDescription = stringResource(id = R.string.signal_0_desc_content),
        modifier = Modifier.size(width = DEFAULT_SIGNAL_SIZE, height = DEFAULT_SIGNAL_SIZE)
    )
}

@Composable
fun MobileSignalIcon1(){
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_signal_1),
        contentDescription = stringResource(id = R.string.signal_1_desc_content),
        modifier = Modifier.size(width = DEFAULT_SIGNAL_SIZE, height = DEFAULT_SIGNAL_SIZE)
    )
}

@Composable
fun MobileSignalIcon2(){
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_signal_2),
        contentDescription = stringResource(id = R.string.signal_2_desc_content),
        modifier = Modifier.size(width = DEFAULT_SIGNAL_SIZE, height = DEFAULT_SIGNAL_SIZE)
    )
}

@Composable
fun MobileSignalIcon3(){
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_signal_3),
        contentDescription = stringResource(id = R.string.signal_3_desc_content),
        modifier = Modifier.size(width = DEFAULT_SIGNAL_SIZE, height = DEFAULT_SIGNAL_SIZE)
    )
}

@Composable
fun MobileSignalIcon4(){
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_signal_3),
        contentDescription = stringResource(id = R.string.signal_3_desc_content),
        modifier = Modifier.size(width = DEFAULT_SIGNAL_SIZE, height = DEFAULT_SIGNAL_SIZE)
    )
}