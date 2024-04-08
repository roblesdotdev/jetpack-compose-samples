package com.roblesdotdev.statedemo.demo.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.roblesdotdev.statedemo.demo.presentation.DemoUIState

@Composable
fun AuthForm(
    modifier: Modifier = Modifier,
    state: DemoUIState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onDismissError: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 54.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DemoTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email ?: "",
            onValueChange = onEmailChange,
            placeholder = "Enter your email",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            leadingIcon = Icons.Default.Email,
        )
        DemoTextField(
            value = state.password ?: "",
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Enter your password",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { onSubmit() }
            ),
            leadingIcon = Icons.Default.Lock,
            isPassword = true,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onSubmit, modifier = Modifier
                .fillMaxWidth()
                .height(52.dp), shape = RoundedCornerShape(8.dp),
            enabled = state.isFormValid()
        ) {
            Text(text = "Sign In")
        }
        state.submissionError?.let { error ->
            AuthErrorDialog(error = error, dismissError = {
                onDismissError()
            })
        }
    }
}