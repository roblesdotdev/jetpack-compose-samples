package com.roblesdotdev.statedemo.demo.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.roblesdotdev.statedemo.R

@Composable
fun AuthErrorDialog(
    modifier: Modifier = Modifier,
    error: String,
    dismissError: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            dismissError()
        },
        confirmButton = {},
        title = {
            Text(
                text = stringResource(R.string.error_title),
                fontSize = 18.sp
            )
        },
        dismissButton = {
            TextButton(onClick = dismissError) {
                Text(text = stringResource(R.string.error_ok_action))
            }
        },
        text = {
            Text(text = error)
        }
    )
}
