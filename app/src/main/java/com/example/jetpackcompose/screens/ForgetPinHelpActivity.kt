package com.example.jetpackcompose.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackcompose.R
import com.example.jetpackcompose.constants.TextStyles
import com.example.jetpackcompose.screens.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.utils.GradientButton
import ir.kaaveh.sdpcompose.sdp

class ForgetPinHelpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ForgetPinHelpContent()
                }
            }
        }
    }
}

@Composable
private fun ForgetPinHelpContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.ic_default_background), contentScale = ContentScale.FillWidth)
    ) {
        val (img, title, subtitle, btn) =  createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_forgot_pin_help),
            contentDescription = "forgot pin help",
            modifier = Modifier.constrainAs(img) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(
            text = "Forgot your wallet pin?",
            style = TextStyles.semiBold18(),
            modifier = Modifier
                .padding(horizontal = 16.sdp)
                .constrainAs(title) {
                    top.linkTo(img.bottom)
                    start.linkTo(parent.start)
                }
        )
        
        Text(
            text = "Please call customer care to reset your PIN.",
            style = TextStyles.semiBold14(Color(0xFF808080)),
            modifier = Modifier
                .padding(horizontal = 16.sdp)
                .constrainAs(subtitle) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                }
        )

        GradientButton(
            text = "Call to reset your PIN",
            textStyle = TextStyles.semiBold18(),
            gradient = Brush.verticalGradient(
                colors = listOf(Color(0xFFE9D298), Color(0xFFAF9252))
            ),
            onClick = { resetCallTapped() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.sdp)
                .constrainAs(btn) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
    }
}

private fun resetCallTapped() {}

@Preview(showBackground = true)
@Composable
fun PreviewForgetPinHelpContent() {
    JetpackComposeTheme {
        ForgetPinHelpContent()
    }
}