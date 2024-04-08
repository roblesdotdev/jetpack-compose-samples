package com.roblesdotdev.statedemo.demo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.roblesdotdev.statedemo.demo.presentation.components.AuthForm
import com.roblesdotdev.statedemo.ui.theme.StateDemoTheme

@Composable
fun DemoScreen(
    viewModel: DemoViewModel
) {
    val state = viewModel.uiState.collectAsState().value
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            AuthForm(
                state = state,
                onEmailChange = {
                    viewModel.onEvent(DemoEvent.EmailChanged(it))
                },
                onPasswordChange = {
                    viewModel.onEvent(DemoEvent.PasswordChanged(it))
                },
                onSubmit = {
                    viewModel.onEvent(DemoEvent.Submitted)
                },
                onDismissError = {
                    viewModel.onEvent(DemoEvent.ErrorDismissed)
                })

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DemoScreenPreview() {
    StateDemoTheme {
        DemoScreen(viewModel = DemoViewModel())
    }
}