package com.roblesdotdev.preferencesdemo.settings.presentation

data class SettingsState(
    val numberOfItems: Int? = null,
    val editEnabled: Boolean = false,
)