package com.roblesdotdev.demoonboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.roblesdotdev.demoonboarding.onboarding.presentation.OnboardingScreen
import com.roblesdotdev.demoonboarding.ui.theme.DemoOnboardingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoOnboardingTheme {
                OnboardingScreen()
            }
        }
    }
}