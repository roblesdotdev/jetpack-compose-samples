package com.roblesdotdev.daggerhiltdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.roblesdotdev.daggerhiltdemo.demo.ui.DemoScreen
import com.roblesdotdev.daggerhiltdemo.demo.ui.DemoViewModel
import com.roblesdotdev.daggerhiltdemo.ui.theme.DaggerHiltDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaggerHiltDemoTheme {
                val demoViewModel = hiltViewModel<DemoViewModel>()
                DemoScreen(
                    state = demoViewModel.state.collectAsState().value,
                    onEvent = demoViewModel::onEvent,
                )
            }
        }
    }
}
