package com.roblesdotdev.roomdemo.presentation

import com.roblesdotdev.roomdemo.domain.model.Contact

data class ContactState(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val showDialog: Boolean = false,
    val isLoading: Boolean = false,
    val contacts: List<Contact> = emptyList(),
    val sortType: SortType = SortType.FIRST_NAME,
) {
    val isValid = firstName.isNotBlank() && lastName.isNotBlank() && phoneNumber.isNotBlank()
}

enum class SortType(val value: String) {
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
}