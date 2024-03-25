package com.example.demosplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.demosplash.ui.theme.DemoSplashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm : SplashViewModel by viewModels()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                vm.showSplash.value
            }
        }

        setContent {
            DemoSplashTheme {
                Text(text = "Hello")
            }
        }
    }
}
