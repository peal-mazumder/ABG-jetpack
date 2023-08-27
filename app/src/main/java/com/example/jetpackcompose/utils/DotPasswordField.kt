package com.example.jetpackcompose.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.jetpackcompose.R
import ir.kaaveh.sdpcompose.sdp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DotPasswordField(
    enteredDigits: MutableState<String>,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box (
        modifier = modifier
    ) {
        BasicTextField(
            value = "",
            onValueChange = { newText ->
                if (newText.isEmpty()) enteredDigits.value = ""
                if (enteredDigits.value.length < 4) {
                    enteredDigits.value += newText
                    if (enteredDigits.value.length == 4) {
                        keyboardController?.hide()
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.NumberPassword,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                },
            ),
            modifier = Modifier
                .background(Color(0xFFD8D8D8), RoundedCornerShape(10.sdp))
                .fillMaxWidth()
                .height(41.sdp),
            cursorBrush = SolidColor(Color.Transparent),
            singleLine = true,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(41.sdp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (i in 0 until 4) {
                if (i >= enteredDigits.value.length) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_not_inputted_dot),
                        contentDescription = "not inputted dot",
                        modifier = if (i != 3)  Modifier.padding(end = 15.sdp) else Modifier,
                    )
                }
                else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_inputted_dot),
                        contentDescription = "inputted dot",
                        modifier = if (i != 3) Modifier.padding(end = 15.sdp) else Modifier,
                    )
                }
            }
        }
    }
}