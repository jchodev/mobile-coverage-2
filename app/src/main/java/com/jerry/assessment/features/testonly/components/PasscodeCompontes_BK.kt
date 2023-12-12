package com.jerry.assessment.features.testonly.components


import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.assessment.R
import com.jerry.assessment.designllib.theme.AssessmentTheme


@Composable
fun PasscodeViewBK(
    modifier: Modifier = Modifier,
    filledDots: State<Int>,
    //viewModel: MainViewModel
) {
    val context = LocalContext.current
    //val filledDots by viewModel.filledDots.collectAsState()
    val xShake = remember { androidx.compose.animation.core.Animatable(initialValue = 0.0F) }
    val passcodeRejectedDialogVisible = remember { mutableStateOf(false) }

//    LaunchedEffect(key1 = true) {
//        viewModel.onPasscodeRejected.collect {
//            passcodeRejectedDialogVisible.value = true
//            vibrateFeedback(context)
//
//            xShake.animateTo(
//                targetValue = 0.dp.value,
//                animationSpec = keyframes {
//                    0.0F at 0
//                    20.0F at 80
//                    -20.0F at 120
//                    10.0F at 160
//                    -10.0F at 200
//                    5.0F at 240
//                    0.0F at 280
//                }
//            )
//        }
//    }

//    PasscodeRejectedDialog(
//        visible = passcodeRejectedDialogVisible.value,
//        onDismiss = {
//            passcodeRejectedDialogVisible.value = false
//
//            viewModel.restart()
//        }
//    )

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.offset(x = xShake.value.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 26.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            repeat(5) { dot ->
                val isFilledDot = dot + 1 <= filledDots.value
                val dotColor = animateColorAsState(
                    if (isFilledDot) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.secondary
                    }
                )

                Box(
                    modifier = modifier
                        .size(size = 14.dp)
                        .background(
                            color = dotColor.value,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
fun PasscodeKeys_bk(
    //viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {

   val onEnterKeyClick: (String) -> Unit = {
       //viewModel.enterKey(it)
   }

    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {


            Row(modifier = Modifier.fillMaxWidth()) {
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "1",
                    onClick = onEnterKeyClick
                )
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "2",
                    onClick = onEnterKeyClick
                )
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "3",
                    onClick = onEnterKeyClick
                )
            }
            Spacer(modifier = Modifier.height(height = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "4",
                    onClick = onEnterKeyClick
                )
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "5",
                    onClick = onEnterKeyClick
                )
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "6",
                    onClick = onEnterKeyClick
                )
            }
            Spacer(modifier = Modifier.height(height = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "7",
                    onClick = onEnterKeyClick
                )
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "8",
                    onClick = onEnterKeyClick
                )
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "9",
                    onClick = onEnterKeyClick
                )
            }
            Spacer(modifier = Modifier.height(height = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                PasscodeKeyBK(modifier = Modifier.weight(weight = 1.0F))
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "0",
                    onClick = onEnterKeyClick
                )
                PasscodeKeyBK(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyIcon = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    keyIconContentDescription = "Delete Passcode Key Button",
                    onClick = {
                        //viewModel.deleteKey()
                    },
                    onLongClick = {
                        //viewModel.deleteAllKeys()
                    }
                )
            }
        }
    }
}

@Composable
fun PasscodeKeyBK(
    modifier: Modifier = Modifier,
    keyTitle: String = "",
    keyIcon: ImageVector? = null,
    keyIconContentDescription: String = "",
    onClick: ((String) -> Unit)? = null,
    onLongClick: (() -> Unit)? = null
) {
    val PasscodeKeyButtonStyle = TextStyle(
//        fontFamily = LatoFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CombinedClickableIconButton_BK(
            modifier = Modifier.padding(4.dp),
            onClick = {
                onClick?.invoke(keyTitle)
            },
            onLongClick = {
                onLongClick?.invoke()
            }
        ) {
            if (keyIcon == null) {
                Text(
                    text = keyTitle,
                    style = PasscodeKeyButtonStyle
                )
            } else {
                Icon(
                    imageVector = keyIcon,
                    contentDescription = keyIconContentDescription
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CombinedClickableIconButton_BK(
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    rippleRadius: Dp = 36.dp,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(size = size)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = rippleRadius,
                    color = if (isSystemInDarkTheme()) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        MaterialTheme.colorScheme.primary
                    }
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        val contentAlpha =
            if (enabled) LocalContentColor.current else MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.38f
            )
        CompositionLocalProvider(LocalContentColor provides contentAlpha, content = content)
    }
}

@Preview(showBackground = true)
@Composable
private fun PasscodeKeyPreview() {
    AssessmentTheme {
        PasscodeKeyBK()
    }
}

@Preview(showBackground = true)
@Composable
private fun PasscodeKeysPreview() {
    AssessmentTheme {
        PasscodeKeys_bk()
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun PasscodeViewPreview() {

    AssessmentTheme {
        val filledDots = mutableStateOf<Int>(0)
        filledDots.value = 6
        PasscodeViewBK(
            filledDots = filledDots
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun CombinedClickableIconButtonPreview(){
//    AssessmentTheme {
//        CombinedClickableIconButton(
//            onClick = {},
//            onLongClick = {},
//            content = {}
//        )
//    }
//}