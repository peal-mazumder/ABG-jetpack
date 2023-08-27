package com.example.jetpackcompose.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.jetpackcompose.R
import com.example.jetpackcompose.constants.TextStyles
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.utils.AppBar
import com.example.jetpackcompose.utils.GradientButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

class LandingPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LandingContent()
                }
            }
        }
    }
}


@Composable
fun LandingContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_wallet),
            contentDescription = "background image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
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
                modifier = Modifier.padding(top = 102.sdp),
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 32.sdp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                val context = LocalContext.current
                GradientButton(
                    text = "Sign Up",
                    textStyle = TextStyles.semiBold18(),
                    gradient = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFF7C7C7C))
                    ),
                    onClick = { signUpButtonTapped(context) },
                    modifier = Modifier.width(124.sdp),
                )
                Spacer(modifier = Modifier.width(16.sdp))
                GradientButton(
                    text = "Log In",
                    textStyle = TextStyles.semiBold18(),
                    gradient = Brush.verticalGradient(
                        colors = listOf(Color(0xFFE9D298), Color(0xFFAF9252))
                    ),
                    onClick = { loginButtonTapped(context) },
                    modifier = Modifier.width(124.sdp),
                )
            }
        }
    }
}

private fun loginButtonTapped(context: Context) {
    context.startActivity(Intent(context, LoginActivity::class.java))
}

private fun signUpButtonTapped(context: Context) {
    context.startActivity(Intent(context, LoginActivity::class.java))
}

@Preview
@Composable
fun PreviewMainContent() {
    LandingContent()
}