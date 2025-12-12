package com.example.assignment3mobiledev

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment3mobiledev.ui.theme.Assignment3MobileDevTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment3MobileDevTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    ) {
                    SecondActivityLayout(
                        context = LocalContext.current
                    )
                    }
            }
        }
    }
}

@Composable
fun SecondActivityLayout(modifier: Modifier = Modifier, context: Context) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "1st Challenge: Android is updated constantly"
        )
        Text(
            text = "2nd Challenge: There are many outdated tutorials / guides"
        )
        Text(
            text = "3rd Challenge: There are many ways of accomplishing certain things"
        )
        Text(
            text = "4th Challenge: Relying on automated activity from IDE may inhibit your learning"
        )
        Text(
            text = "5th Challenge: You are developing for global audiences"
        )
        Button (
            onClick = { context.startActivity(Intent(context, MainActivity::class.java)) }
        ) {
            Text(
                text = "Main Activity"
            )
        }
    }
}