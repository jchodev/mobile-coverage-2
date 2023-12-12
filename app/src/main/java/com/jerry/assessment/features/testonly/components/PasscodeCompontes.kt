package com.jerry.assessment.features.testonly.components



import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.assessment.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.jerry.assessment.designllib.theme.AssessmentTheme

@Composable
fun PasscodeView(
    modifier: Modifier = Modifier,
    maxLength: Int = 5,
    passcode: State<String>,
) {
    val xShake = remember { androidx.compose.animation.core.Animatable(initialValue = 0.0F) }

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
            repeat(maxLength) { dot ->
                val isFilledDot = dot + 1 <= passcode.value.length
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
fun PasscodeKeys(
    onClick: ((String?) -> Unit) = {},
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "1",
                    onClick = {
                        onClick.invoke("1")
                    }
                )
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "2",
                    onClick = {
                        onClick.invoke("2")
                    }
                )
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "3",
                    onClick = {
                        onClick.invoke("3")
                    }
                )
            }
            Spacer(modifier = Modifier.height(height = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "4",
                    onClick = {
                        onClick.invoke("1")
                    }
                )
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "5",
                    onClick = {
                        onClick.invoke("5")
                    }
                )
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "6",
                    onClick = {
                        onClick.invoke("6")
                    }
                )
            }
            Spacer(modifier = Modifier.height(height = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "7",
                    onClick = {
                        onClick.invoke("7")
                    }
                )
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "5",
                    onClick = {
                        onClick.invoke("8")
                    }
                )
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "9",
                    onClick = {
                        onClick.invoke("9")
                    }
                )
            }
            Spacer(modifier = Modifier.height(height = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(weight = 1.0F))
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyTitle = "0",
                    onClick = {
                        onClick.invoke("0")
                    }
                )
                PasscodeKey(
                    modifier = Modifier.weight(weight = 1.0F),
                    keyIcon = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    keyIconContentDescription = "Delete Passcode Key Button",
                    onClick = {
                        onClick.invoke(null)
                    }
                )
            }
        }
    }
}

@Composable
fun PasscodeKey(
    modifier: Modifier = Modifier,
    keyTitle: String? = null,
    keyIcon: ImageVector? = null,
    keyIconContentDescription: String = "",
    onClick: ((String?) -> Unit)? = null
) {
    val PasscodeKeyButtonStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (keyTitle != null || keyIcon != null) {
            CombinedClickableIconButton(
                modifier = Modifier.padding(4.dp),
                onClick = { onClick!!.invoke(keyTitle) },
            ) {
                if (keyIcon == null) {
                    Text(
                        text = keyTitle!!,
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CombinedClickableIconButton(
    onClick: () -> Unit,
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

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun PasscodeViewPreview() {

    AssessmentTheme {
        val passcode = mutableStateOf<String>("2")

        Column(modifier = Modifier.fillMaxWidth()) {
            PasscodeView(
                passcode = passcode
            )

            Spacer(modifier = Modifier.height(height = 12.dp))

            PasscodeKeys(
                onClick = {
                    if (passcode.value.isNotEmpty()) {
                        if (it == null) {
                            passcode.value = passcode.value.dropLast(1)
                        } else {
                            passcode.value = passcode.value + it
                        }
                    }
                }
            )
        }
    }
}


