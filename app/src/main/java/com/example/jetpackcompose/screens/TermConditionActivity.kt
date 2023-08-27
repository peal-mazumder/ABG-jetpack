package com.example.jetpackcompose.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jetpackcompose.R
import com.example.jetpackcompose.constants.TextStyles
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.utils.GradientButton
import com.example.jetpackcompose.utils.HeadingAppBar
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

class TermConditionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TermConditionContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("InvalidColorHexValue")
@Composable
fun TermConditionContent() {
    val checkedState = remember { mutableStateOf(false) }

    Image(
        painter = painterResource(id = R.drawable.ic_default_background),
        contentDescription = "background image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        GradientButton(
            enabled = checkedState.value,
            text = "Proceed",
            textStyle = TextStyles.semiBold18(),
            gradient = if (checkedState.value)
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFE9D298), Color(0xFFAF9252))
                ) else Brush.verticalGradient(
                colors = listOf(Color(0xFFF8E4DCC0), Color(0xFFF0E2D0AD))
            ),
            onClick = { proceedButtonTapped() },
            modifier = Modifier.padding(bottom = 31.sdp, start = 19.sdp, end = 19.sdp),
            textModifier = if(checkedState.value) Modifier.alpha(1f) else Modifier.alpha(0.7f)
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeadingAppBar("Terms & Conditions", trailIcon = false)
        WebViewContent()
        Row(
            modifier = Modifier
                .padding(top = 15.sdp, start = 19.sdp, end = 19.sdp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF3EAE6F),
                            checkmarkColor = Color.White,
                            uncheckedColor = Color(0xFF3EAE6F),
                        ),
                    )
                }
                Text(
                    text = "I agree",
                    style = TextStyle(
                        fontSize = 11.ssp,
                        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier.padding(start = 8.sdp),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = "download icon",
                modifier = Modifier.clickable {  }
            )
        }
    }
}

private fun proceedButtonTapped() {
    Log.d("BUTTON", "Proceed button tapped")
}

@Composable
fun WebViewContent() {
    val mUrl = "https://www.geeksforgeeks.org"
    AndroidView(
        modifier = Modifier
            .padding(top = 23.sdp, start = 19.sdp, end = 19.sdp)
            .height(405.sdp)
            .fillMaxWidth()
        ,
        factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewTermConditionContent() {
    JetpackComposeTheme {
        TermConditionContent()
    }
}