package com.roblesdotdev.daggerhiltdemo.demo.ui

import androidx.lifecycle.ViewModel
import com.roblesdotdev.daggerhiltdemo.demo.domain.usecase.GetMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(
    getMessageUseCase: GetMessageUseCase,
) : ViewModel() {
    val state = MutableStateFlow(DemoState())

    init {
        val message = getMessageUseCase()
        state.update { prevState -> prevState.copy(message = message) }
    }

    fun onEvent(event: DemoEvent) {
        when (event) {
            is DemoEvent.MessageChanged -> updateMessage(event.message)
            DemoEvent.ResetMessage -> resetMessage()
        }
    }

    private fun resetMessage() {
        state.update { prevState ->
            prevState.copy(message = null)
        }
    }

    private fun updateMessage(message: String) {
        state.update { prevState ->
            prevState.copy(
                message = message
            )
        }
    }
}