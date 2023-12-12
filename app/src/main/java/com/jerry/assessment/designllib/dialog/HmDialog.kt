package com.jerry.assessment.designllib.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jerry.assessment.designllib.theme.AssessmentTheme


@Composable
fun HmAlertDialog(
    title: String = "",
    message: String = "",

    leftBtnStr: String = "Dismiss",
    onLeftClick: () -> Unit,

    rightBtnStr: String = "OK",
    onRightClick: () -> Unit
) {

    Dialog(
        onDismissRequest = { onLeftClick() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(10.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp)
            ) {

                //title
                Text(
                    text = title,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp
                )

                //message
                Text(
                    text = message,
                    modifier = Modifier.padding(8.dp)
                )

                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        onClick = { onLeftClick() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = leftBtnStr)
                    }

                    Button(
                        onClick = { onRightClick() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = rightBtnStr)
                    }
                }
            }
        }
    }
}

@Composable
fun NormalMessageDialog(
    title: String = "",
    message: String = "",

    oKStr: String = "Ok",
    onOKClick: () -> Unit
) {

    Dialog(
        onDismissRequest = { onOKClick() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(10.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                //title
                Text(
                    text = title,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp
                )

                //message
                Text(
                    text = message,
                    modifier = Modifier.padding(8.dp)
                )

                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        onClick = { onOKClick() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = oKStr)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CustomAlertDialogLightPreview(){
    AssessmentTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        HmAlertDialog(
            title = "this is title",
            message = "this is message",
            onLeftClick = {},
            onRightClick = {}
        )
    }
}

@Preview
@Composable
private fun NormalMessageDialogDarkPreview(){
    AssessmentTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        NormalMessageDialog(
            title = "this is title",
            message = "this is message",
            onOKClick = {}
        )
    }
}

@Preview
@Composable
private fun NormalMessageDialogLightPreview(){
    AssessmentTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        NormalMessageDialog(
            title = "this is title",
            message = "this is message",
            onOKClick = {}
        )
    }
}

@Preview
@Composable
private fun CustomAlertDialogDarkPreview(){
    AssessmentTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        HmAlertDialog(
            title = "this is title",
            message = "this is message",
            onLeftClick = {},
            onRightClick = {}
        )
    }
}