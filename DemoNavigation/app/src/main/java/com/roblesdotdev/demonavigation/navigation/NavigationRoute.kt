package com.roblesdotdev.demonavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.roblesdotdev.demonavigation.home.presentation.HomeScreen
import com.roblesdotdev.demonavigation.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: NavRoute,
) {
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(NavRoute.Onboarding.route) {
            OnboardingScreen(onFinish = {
                navController.popBackStack()
                navController.navigate(NavRoute.Home.route)
            })
        }

        composable(NavRoute.Home.route) {
            HomeScreen()
        }
    }
}