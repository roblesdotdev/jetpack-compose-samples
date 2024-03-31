package com.roblesdotdev.demonavigation.navigation

sealed class NavRoute(val route: String) {
    data object Onboarding : NavRoute("onboarding")
    data object Home : NavRoute("home")
}