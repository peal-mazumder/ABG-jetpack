package com.example.jetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.jetpackcompose.screens.DashBoardActivity
import com.example.jetpackcompose.screens.ForgetPinHelpActivity
import com.example.jetpackcompose.screens.LandingPageActivity
import com.example.jetpackcompose.screens.SignUpActivity
import com.example.jetpackcompose.screens.TermConditionActivity
import com.example.jetpackcompose.screens.VerifyOtpActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, DashBoardActivity::class.java))
    }
}
