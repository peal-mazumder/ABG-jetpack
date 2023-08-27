package com.example.jetpackcompose.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.utils.HeadingAppBar
import com.example.jetpackcompose.constants.TextStyles
import com.example.jetpackcompose.data.OperatorData
import com.example.jetpackcompose.data.OperatorModel
import com.example.jetpackcompose.utils.GradientButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignupContent()
                }
            }
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignupContent() {
    Image(
        painter = painterResource(id = R.drawable.ic_default_background),
        contentDescription = "background image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
    )

    val phoneNo = remember { mutableStateOf("") }
    val operators = remember { mutableStateOf(OperatorData.operators) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        HeadingAppBar("Sign Up")

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (body, bottomBtn) = createRefs()

            Column(
                modifier = Modifier
                    .padding(horizontal = 19.sdp)
                    .constrainAs(body) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            ) {
                Text(
                    text = "Mobile Number",
                    style = TextStyles.regular18(),
                    modifier = Modifier.padding(top = 73.sdp),
                )
                Row(modifier = Modifier.padding(top = 15.sdp)) {
                    TextField(
                        value = "+88",
                        textStyle = TextStyles.semiBold18(),
                        readOnly = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        onValueChange = {},
                        modifier = Modifier
                            .width(52.sdp)
                            .border(
                                width = 1.sdp,
                                color = Color(0xFFD8D8D8),
                                shape = RoundedCornerShape(10.sdp),
                            ),
                    )
                    Spacer(modifier = Modifier.width(8.sdp))
                    TextField(
                        value = phoneNo.value,
                        textStyle = TextStyles.regular18(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done
                        ),
                        onValueChange = { it -> phoneNo.value = it },
                        modifier = Modifier
                            .border(
                                width = 1.sdp,
                                color = Color(0xFFD8D8D8),
                                shape = RoundedCornerShape(10.sdp),
                            )
                            .fillMaxWidth(),
                    )
                }
                Text(
                    text = "Select Operator",
                    style = TextStyles.regular18(),
                    modifier = Modifier.padding(top = 31.sdp, bottom = 8.sdp),
                )
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(50.sdp),
                    contentPadding = PaddingValues(
                        top = 8.sdp,
                        bottom = 8.sdp,
                    ),
                    content = {
                        items(operators.value.size) {
                            OperatorItem(it, operators)
                        }
                    },
                    horizontalArrangement = Arrangement.spacedBy(15.sdp),
                    verticalArrangement = Arrangement.spacedBy(15.sdp),
                )
            }

            BottomButton(
                modifier = Modifier
                    .constrainAs(bottomBtn) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = 31.sdp, start = 19.sdp, end = 19.sdp),
                phoneNo = phoneNo
            )
        }
    }
}

@Composable
private fun OperatorItem(
    position: Int,
    operators: MutableState<MutableList<OperatorModel>>
) {
    val stroke = Stroke(width = 20f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(24f, 24f), 0f)
    )

    Card(
        modifier = Modifier
            .clickable {
                operators.value.forEach {
                    Log.d("DATA", it.hasBorder.toString())
                    it.hasBorder = it == operators.value[position]
                }
            }
            .height(55.sdp)
            .width(55.sdp)
            .drawBehind {
                if (operators.value[position].hasBorder) {
                    Log.d("DATA", "$position = ${operators.value[position].hasBorder}")
                    drawRoundRect(
                        color = Color(0xFF3EAE6F),
                        style = stroke,
                        cornerRadius = CornerRadius(13.dp.toPx())
                    )
                }
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Image(
            painter = painterResource(id = operators.value[position].img),
            contentDescription = operators.value[position].name,
            contentScale = ContentScale.None,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@SuppressLint("InvalidColorHexValue")
@Composable
private fun BottomButton(
    modifier: Modifier,
    phoneNo: MutableState<String>,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row {
            Text(text = "Already have an account? ", style = TextStyles.regular14())
            Text(
                text = "Log In",
                style = TextStyles.semiBold14(Color(0xFF3EAE6F)),
                modifier = Modifier.clickable {}
            )
            Text(text = " here.", style = TextStyles.regular14())
        }
        GradientButton(
            enabled = phoneNo.value.isNotEmpty(),
            text = "Verify",
            textStyle = TextStyles.semiBold18(),
            gradient = if (phoneNo.value.isNotEmpty())
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFE9D298), Color(0xFFAF9252))
                ) else Brush.verticalGradient(
                colors = listOf(Color(0xFFF8E4DCC0), Color(0xFFF0E2D0AD))
            ),
            onClick = { verifyButtonTapped() },
            modifier = Modifier.fillMaxWidth().padding(top = 15.sdp),
            textModifier = if(phoneNo.value.isNotEmpty()) Modifier.alpha(1f) else Modifier.alpha(0.7f)
        )
    }
}

private fun verifyButtonTapped() {}

@Preview(showBackground = true)
@Composable
fun Preview() {
    JetpackComposeTheme {
        SignupContent()
    }
}