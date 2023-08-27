package com.example.jetpackcompose.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    textStyle: androidx.compose.ui.text.TextStyle,
    gradient: Brush,
    onClick: () -> Unit,
) {
    Button(
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        onClick = { onClick() },
        modifier = modifier,
        shape = RoundedCornerShape(10.sdp),
        contentPadding = PaddingValues(0.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = gradient, shape = RoundedCornerShape(10.sdp))
                .padding(vertical = 10.sdp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = textStyle,
                modifier = textModifier,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGradientButton() {
    GradientButton(
        text = "Proceed",
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 14.ssp,
            fontFamily = FontFamily(Font(R.font.open_sans_regular)),
            fontWeight = FontWeight(600),
            color = Color(0xB2000000),
        ),
        gradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFE9D298), Color(0xFFAF9252))
        ),
        onClick = {},
        modifier = Modifier.padding(bottom = 0.sdp),
    )
}