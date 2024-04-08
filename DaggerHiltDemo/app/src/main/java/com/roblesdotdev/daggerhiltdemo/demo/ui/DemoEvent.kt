package com.roblesdotdev.daggerhiltdemo.demo.ui

sealed interface DemoEvent {
    data class MessageChanged(val message: String) : DemoEvent
    data object ResetMessage : DemoEvent
}