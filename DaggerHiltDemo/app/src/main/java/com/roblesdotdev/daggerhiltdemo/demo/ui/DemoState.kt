package com.roblesdotdev.daggerhiltdemo.demo.ui

data class DemoState(
    val message: String? = null
) {
    fun isValid() : Boolean {
        return message?.isNotEmpty() == true
    }
}