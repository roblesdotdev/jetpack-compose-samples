package com.roblesdotdev.statedemo.demo.presentation

data class DemoUIState(
    val isLoading: Boolean = false,
    val email: String? = null,
    val password: String? = null,
    val submissionError: String? = null,
) {
    fun isFormValid(): Boolean {
        return email?.isNotEmpty() == true && password?.isNotEmpty() == true
    }
}