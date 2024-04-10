package com.roblesdotdev.preferencesdemo.navigation

sealed class NavigationRoute(val route: String) {
    data object Settings : NavigationRoute("settings")
}