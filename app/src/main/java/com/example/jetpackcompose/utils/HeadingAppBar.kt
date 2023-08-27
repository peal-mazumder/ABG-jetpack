package com.example.jetpackcompose.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun HeadingAppBar(
    title: String = "Activity Name",
    trailIcon: Boolean=true,
    modifier: Modifier = Modifier,
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 19.sdp, end = 19.sdp, top = 10.sdp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_backward),
            contentDescription = "backward icon",
            modifier = Modifier.clickable {},
        )
        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.ssp,
                fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
        )
        if (trailIcon) {
            Image(
                painter = painterResource(id = R.drawable.ic_support),
                contentDescription = "Support icon",
                modifier = Modifier.clickable {},
            )
        } else {
            Box(modifier = Modifier)
        }
    }
}

@Preview
@Composable
fun PreviewHeadingAppBar() {
    HeadingAppBar()
}