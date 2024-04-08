package com.roblesdotdev.statedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.roblesdotdev.statedemo.demo.presentation.DemoScreen
import com.roblesdotdev.statedemo.demo.presentation.DemoViewModel
import com.roblesdotdev.statedemo.ui.theme.StateDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateDemoTheme {
                val demoViewModel = DemoViewModel()
                DemoScreen(viewModel = demoViewModel)
            }
        }
    }
}
