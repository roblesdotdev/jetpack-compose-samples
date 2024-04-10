package com.roblesdotdev.preferencesdemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.roblesdotdev.preferencesdemo.settings.presentation.SettingsScreen
import com.roblesdotdev.preferencesdemo.settings.presentation.SettingsViewModel

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: NavigationRoute
) {
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(NavigationRoute.Settings.route) {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val state by settingsViewModel.state.collectAsStateWithLifecycle()
            SettingsScreen(
                uiState = state,
                onEvent = settingsViewModel::onEvent,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}