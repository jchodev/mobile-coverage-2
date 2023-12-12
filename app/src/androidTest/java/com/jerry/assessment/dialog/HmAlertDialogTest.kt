package com.jerry.assessment.designlib.dialog

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.jerry.assessment.designllib.dialog.HmAlertDialog
import org.junit.Rule
import org.junit.Test

class HmAlertDialogTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testWithExpected() {
        //assign
        rule.setContent {
            HmAlertDialog(
                title = "title",
                message = "message",
                onLeftClick = {  },
                rightBtnStr = "retry",
                onRightClick = { }
            )
        }

        //check
        rule.onNodeWithText("title").assertExists()
        rule.onNodeWithText("message").assertExists()
    }
}