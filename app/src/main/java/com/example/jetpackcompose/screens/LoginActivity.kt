package com.example.jetpackcompose.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.jetpackcompose.R
import com.example.jetpackcompose.utils.AppBar
import com.example.jetpackcompose.utils.DotPasswordField
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.utils.GradientButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent() {
    val enteredDigits = remember { mutableStateOf("") }
    val phoneNo = remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_wallet),
            contentDescription = "background image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            GradientButton(
                text = "Proceed",
                textStyle = TextStyle(
                    fontSize = 14.ssp,
                    fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xB2000000),
                ),
                gradient = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE9D298), Color(0xFFAF9252))
                ),
                onClick = { proceedButtonTapped() },
                modifier = Modifier.padding(bottom = 31.sdp, start = 19.sdp, end = 19.sdp),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.sdp, end = 16.sdp, top = 16.sdp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppBar()
            Image(
                painter = painterResource(id = R.drawable.ic_main_green_logo),
                contentDescription = "main logo",
                modifier = Modifier.padding(top = 46.sdp),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 62.sdp),
            ) {
                CustomText("Mobile Number")
                Row(
                    modifier = Modifier.padding(top = 8.sdp),
                ) {
                    val textStyle = TextStyle(
                        fontSize = 14.ssp,
                        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                        fontWeight = FontWeight(600),
                        color = Color(0xB2000000),
                    )
                    TextField(
                        value = "+88",
                        textStyle = textStyle,
                        readOnly = true,
                        shape = RoundedCornerShape(10.sdp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFD8D8D8),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        onValueChange = {},
                        modifier = Modifier
                            .width(52.sdp),
                    )
                    Spacer(modifier = Modifier.width(8.sdp))
                    TextField(
                        value = phoneNo.value,
                        textStyle = textStyle,
                        shape = RoundedCornerShape(10.sdp),
                        onValueChange = { newText ->
                            if (phoneNo.value.length < 11) {
                                phoneNo.value = newText
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFD8D8D8),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                    )
                }
                CustomText("Enter PIN", modifier = Modifier.padding(top = 23.sdp))
                DotPasswordField(enteredDigits, Modifier.padding(top = 8.sdp))
                Text(
                    text = "Forgot PIN?",
                    modifier = Modifier
                        .padding(top = 15.sdp)
                        .fillMaxWidth()
                        .clickable { forgotPinTapped() },
                    style = TextStyle(
                        fontSize = 11.ssp,
                        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                        fontWeight = FontWeight(600),
                        color = Color(0x80FFFFFF),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

private fun forgotPinTapped() {}

private fun proceedButtonTapped() {}

@Composable
fun CustomText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = 14.ssp,
            fontFamily = FontFamily(Font(R.font.open_sans_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xB2FFFFFF),
        )
    )
}

@Preview()
@Composable
fun PreviewLoginContent() {
    LoginContent()
}