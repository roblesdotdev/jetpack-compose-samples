package com.roblesdotdev.preferencesdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.roblesdotdev.preferencesdemo.navigation.NavigationHost
import com.roblesdotdev.preferencesdemo.navigation.NavigationRoute
import com.roblesdotdev.preferencesdemo.ui.theme.PreferencesDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoApplication()
        }
    }
}

@Composable
fun DemoApplication() {
    PreferencesDemoTheme {
        val navController = rememberNavController()
        NavigationHost(
            navController = navController,
            startDestination = NavigationRoute.Settings)
    }
}