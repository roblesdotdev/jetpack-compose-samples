package com.roblesdotdev.demonavigation.onboarding.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    Column {
        Text("Welcome to our awesome app")
        Button(onClick = onFinish) {
            Text(text = "Get Started")
        }
    }
}