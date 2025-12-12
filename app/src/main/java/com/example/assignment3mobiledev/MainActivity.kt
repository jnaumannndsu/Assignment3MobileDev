package com.example.assignment3mobiledev

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment3mobiledev.ui.theme.Assignment3MobileDevTheme
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val perms = arrayOf("com.example.assignment3mobiledev.MSE412")
        val permnumber = 200
        requestPermissions(perms, permnumber)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment3MobileDevTheme {
                    HomeScreenLayout(context = LocalContext.current)
                }
            }
        }
    }
@Override
fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    when(requestCode) {
        200 -> {
            if((grantResults.isNotEmpty()) && grantResults[0] == PackageManager.PERMISSION_GRANTED) return
        }
    }
}

@Composable
fun HomeScreenLayout(modifier: Modifier = Modifier, context: Context) {
    val intent = Intent(Intent.ACTION_MAIN)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Name: Jackson Naumann"
        )
        Text(
            text = "ID: 1286262"
        )
        Row() {
            Button(
                onClick = {context.startActivity(intent) }
            ) {
                Text(
                    text = "Start Activity Implicitly"
                )
            }
            Button(
                onClick = {context.startActivity(Intent(context, MainActivity2::class.java))}
            ) {
                Text(
                    text = "Start Activity Explicitly"
                )
            }
            Button(
                onClick = {context.startActivity(Intent(context, CameraActivity::class.java))}
            ) {
                Text("View Image Activity")
            }
        }
    }
}