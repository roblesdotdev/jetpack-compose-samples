package com.roblesdotdev.preferencesdemo.settings.presentation

sealed interface SettingsEvent {
    data class NumberChanged(val number: Int?) : SettingsEvent
    data class EnabledChanged(val enabled: Boolean) : SettingsEvent
    data object SaveSettings : SettingsEvent
}