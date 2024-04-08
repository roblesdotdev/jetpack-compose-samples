package com.roblesdotdev.statedemo.demo.presentation

sealed interface DemoEvent {
    data class EmailChanged(val email: String) : DemoEvent
    data class PasswordChanged(val password: String) : DemoEvent
    data object ErrorDismissed : DemoEvent
    data object Submitted : DemoEvent
}