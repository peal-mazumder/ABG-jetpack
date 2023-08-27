package com.example.jetpackcompose.constants

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.jetpackcompose.R
import ir.kaaveh.sdpcompose.ssp

object TextStyles {
    @Composable
    fun regular14(color: Color = Color(0xB2000000)) = TextStyle(
        fontSize = 11.ssp,
        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
        fontWeight = FontWeight(400),
        color = color,
    )

    @Composable
    fun regular18(color: Color = Color(0xB2000000)) = TextStyle(
        fontSize = 14.ssp,
        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
        fontWeight = FontWeight(400),
        color = color,
    )

    @Composable
    fun semiBold14(color: Color = Color(0xB2000000)) = TextStyle(
        fontSize = 11.ssp,
        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
        fontWeight = FontWeight(600),
        color = color,
    )

    @Composable
    fun semiBold18(color: Color = Color(0xB2000000)) = TextStyle(
        fontSize = 14.ssp,
        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
        fontWeight = FontWeight(600),
        color = color,
    )
}