package com.roblesdotdev.daggerhiltdemo.demo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DemoScreen(
    state: DemoState,
    onEvent: (DemoEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = if (state.message?.isNotEmpty() == true) state.message else "Enter a message!")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text(text = "Enter a message") },
            value = state.message ?: "", onValueChange = {
                onEvent(DemoEvent.MessageChanged(it))
            })
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = state.isValid(),
            shape = RoundedCornerShape(0.dp),
            onClick = {
                onEvent(DemoEvent.ResetMessage)
            }) {
            Text(text = "Reset")
        }
    }
}