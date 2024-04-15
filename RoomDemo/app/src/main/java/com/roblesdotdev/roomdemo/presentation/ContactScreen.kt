package com.roblesdotdev.roomdemo.presentation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(ContactEvent.ShowDialog) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->

        if (state.showDialog) {
            AddContactDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SortType.entries.toTypedArray().forEach { sortType ->
                        Row(
                            Modifier.selectable(
                                selected = state.sortType == sortType,
                                onClick = { onEvent(ContactEvent.SetSortType(sortType)) },
                                role = Role.RadioButton,
                            )
                        ) {
                            RadioButton(selected = state.sortType == sortType, onClick = null)
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            items(state.contacts, key = { it.id }) { contact ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "${contact.firstName} ${contact.lastName}")
                        Text(text = contact.phoneNumber)
                    }
                    IconButton(onClick = { onEvent(ContactEvent.DeleteContact(contact)) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }

            }

        }
    }
}

@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onEvent(ContactEvent.HideDialog) },
        confirmButton = {
            Button(onClick = { onEvent(ContactEvent.SaveContact) }, enabled = state.isValid) {
                Text(text = "Save contact")
            }
        },
        dismissButton = {
                        TextButton(onClick = {
                            onEvent(ContactEvent.Reset)
                        }) {
                            Text(text = "Cancel")
                        }
        },
        title = { Text("add contact") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                TextField(value = state.firstName, onValueChange = {
                    onEvent(ContactEvent.SetFirstName(it))
                }, placeholder = { Text("First Name") })
                TextField(value = state.lastName, onValueChange = {
                    onEvent(ContactEvent.SetLastName(it))
                }, placeholder = { Text("Last Name") })
                TextField(value = state.phoneNumber, onValueChange = {
                    onEvent(ContactEvent.SetPhoneNumber(it))
                }, placeholder = { Text("Phone Number") })
            }
        },
    )

}