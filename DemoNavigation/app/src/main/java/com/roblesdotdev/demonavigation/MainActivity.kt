package com.roblesdotdev.demonavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.roblesdotdev.demonavigation.navigation.NavRoute
import com.roblesdotdev.demonavigation.navigation.NavigationHost
import com.roblesdotdev.demonavigation.ui.theme.DemoNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoNavigationTheme {
                val navController = rememberNavController()
                NavigationHost(navController = navController, startDestination = NavRoute.Onboarding)
            }
        }
    }
}