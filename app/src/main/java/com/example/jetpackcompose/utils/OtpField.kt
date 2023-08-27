package com.example.jetpackcompose.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.jetpackcompose.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun OtpField(
    otp1: MutableState<String>,
    otp2: MutableState<String>,
    otp3: MutableState<String>,
    otp4: MutableState<String>,
) {
    val focusRequesters = List(4) { FocusRequester() }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextFieldWithFocus(otp1, focusRequesters[0], focusRequesters[1])
        TextFieldWithFocus(otp2, focusRequesters[1], focusRequesters[2])
        TextFieldWithFocus(otp3, focusRequesters[2], focusRequesters[3])
        TextFieldWithFocus(otp4, focusRequesters[3], focusRequesters[0], true)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldWithFocus(
    data: MutableState<String>,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester,
    last: Boolean = false,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .width(39.sdp)
            .height(39.sdp)
            .border(
                width = 1.sdp,
                color = Color(0x80000000),
                shape = RoundedCornerShape(8.sdp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        BasicTextField(
            value = data.value.ifEmpty { "" },
            onValueChange = { value ->
                data.value = if (value.isNotEmpty()) value.last().toString() else ""
                if (last) keyboardController?.hide()
                else {
                    if (value.isNotEmpty()) {
                        nextFocusRequester.requestFocus()
                    }
                }
            },
            modifier = Modifier.fillMaxSize().focusRequester(focusRequester).padding(vertical = 11.sdp),
            cursorBrush = SolidColor(Color.Transparent),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = if (last) ImeAction.Done else ImeAction.Next,
            ),
            textStyle = TextStyle(
                fontSize = 12.ssp,
                fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            )
        )
    }
}