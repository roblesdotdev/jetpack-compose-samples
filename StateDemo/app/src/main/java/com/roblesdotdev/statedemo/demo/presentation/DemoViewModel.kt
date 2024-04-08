package com.roblesdotdev.statedemo.demo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel : ViewModel() {
    val uiState = MutableStateFlow(DemoUIState())

    fun onEvent(event: DemoEvent) {
        when (event) {
            is DemoEvent.EmailChanged -> updateEmail(event.email)
            is DemoEvent.PasswordChanged -> updatePassword(event.password)
            DemoEvent.Submitted -> login()
            DemoEvent.ErrorDismissed -> dismissError()
        }
    }

    private fun login() {
        uiState.update { prevState -> prevState.copy(isLoading = true) }
        viewModelScope.launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                uiState.update { prevState -> prevState.copy(
                    isLoading = false,
                    submissionError = "Login not implemented yet!"
                ) }
            }
        }
    }

    private fun updateEmail(email: String) {
        uiState.update { prevState ->
            prevState.copy(email = email)
        }
    }

    private fun updatePassword(password: String) {
        uiState.update { prevState ->
            prevState.copy(password = password)
        }
    }

    private fun dismissError() {
        uiState.update { prevState ->
            prevState.copy(submissionError = null)
        }
    }
}