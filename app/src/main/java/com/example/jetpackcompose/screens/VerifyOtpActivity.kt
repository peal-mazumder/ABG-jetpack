package com.example.jetpackcompose.screens

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.jetpackcompose.R
import com.example.jetpackcompose.constants.TextStyles
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.utils.GradientButton
import com.example.jetpackcompose.utils.HeadingAppBar
import com.example.jetpackcompose.utils.OtpField
import com.example.jetpackcompose.utils.rememberImeState
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.delay

class VerifyOtpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VerifyOtpContent()
                }
            }
        }
    }
}

@SuppressLint("InvalidColorHexValue")
@Composable
fun VerifyOtpContent() {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.scrollTo(scrollState.maxValue)
        }
    }

    val timeLeft = remember { mutableStateOf(40) }
    val startTimer = remember { mutableStateOf(true) }

    if (startTimer.value) {
        CountDownTimer(timeLeft, startTimer)
    }

    val otp1 = remember { mutableStateOf("") }
    val otp2 = remember { mutableStateOf("") }
    val otp3 = remember { mutableStateOf("") }
    val otp4 = remember { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.ic_default_background),
                contentScale = ContentScale.FillWidth
            )
    ) {
        val (topBar, gif, heading, btn, otp) = createRefs()

        HeadingAppBar(
            "Verify OTP",
            modifier = Modifier.constrainAs(topBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        GifImage(
            modifier = Modifier
                .constrainAs(gif) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topBar.bottom)
                }
                .height(254.sdp)
                .width(154.sdp)
                .padding(top = 51.sdp)
        )

        Text(
            text = "An SMS with OTP\nhas been sent to your number",
            style = TextStyle(
                fontSize = 17.ssp,
                fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(top = 15.sdp)
                .constrainAs(heading) {
                    top.linkTo(gif.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Column(
            modifier = Modifier
                .padding(bottom = 31.sdp, start = 30.sdp, end = 30.sdp)
                .constrainAs(otp) {
                    bottom.linkTo(btn.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            OtpField(otp1, otp2, otp3, otp4)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.sdp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Resend OTP",
                    modifier = Modifier.clickable {
                        if (timeLeft.value == 0) resendOTPTapped(timeLeft, startTimer)
                    },
                    style = TextStyle(
                        fontSize = 11.ssp,
                        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                        fontWeight = if(timeLeft.value>0) FontWeight(400) else FontWeight(600),
                        color = if(timeLeft.value>0) Color(0x80000000) else Color(0xFF3EAE6F),
                    )
                )
                if(timeLeft.value > 0) {
                    Text(
                        text = convertTime(timeLeft.value),
                        modifier = Modifier.padding(start = 15.sdp),
                        style = TextStyle(
                            fontSize = 11.ssp,
                            fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                            fontWeight = FontWeight(700),
                            color = Color(0x80000000),
                        )
                    )
                }
            }
        }

        GradientButton(
            enabled = (otp1.value.isNotEmpty() && otp2.value.isNotEmpty()
                    && otp3.value.isNotEmpty() && otp4.value.isNotEmpty()),
            text = "Verify",
            textStyle = TextStyles.semiBold18(),
            gradient = if (otp1.value.isNotEmpty() && otp2.value.isNotEmpty()
                && otp3.value.isNotEmpty() && otp4.value.isNotEmpty())
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFE9D298), Color(0xFFAF9252))
                ) else Brush.verticalGradient(
                colors = listOf(Color(0xFFF8E4DCC0), Color(0xFFF0E2D0AD))
            ),
            onClick = { verifyButtonTapped(otp1,otp2,otp3,otp4) },
            modifier = Modifier
                .padding(bottom = 31.sdp, start = 19.sdp, end = 19.sdp)
                .constrainAs(btn) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            textModifier = if(otp1.value.isNotEmpty() && otp2.value.isNotEmpty()
                && otp3.value.isNotEmpty() && otp4.value.isNotEmpty()) Modifier.alpha(1f) else Modifier.alpha(0.3f)
        )
    }
}

@Composable
fun CountDownTimer(
    timeLeft: MutableState<Int>,
    startTimer: MutableState<Boolean>
) {
    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft.value > 0) {
            delay(1000L)
            timeLeft.value--
        }
    }
    if (timeLeft.value == 0) {
        startTimer.value = false
    }
}

@Composable
fun GifImage(modifier: Modifier) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.verify_otp_animation).build(),
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier
    )
}

private fun convertTime(time: Int): String {
    var minutes = (time / 60).toString()
    var seconds = (time % 60).toString()

    if (minutes.length == 1) minutes = "0$minutes"
    if (seconds.length == 1) seconds = "0$seconds"

    return "$minutes:$seconds"
}

private fun resendOTPTapped(
    timeLeft: MutableState<Int>,
    startTimer: MutableState<Boolean>
) {
    timeLeft.value = 40
    startTimer.value = true
}

private fun verifyButtonTapped(
    otp1: MutableState<String>,
    otp2: MutableState<String>,
    otp3: MutableState<String>,
    otp4: MutableState<String>,
) {
    Log.d("OTP", otp1.value+otp2.value+otp3.value+otp4.value)
}

@Preview(showBackground = true)
@Composable
fun PreviewVerifyOtpContent() {
    JetpackComposeTheme {
        VerifyOtpContent()
    }
}