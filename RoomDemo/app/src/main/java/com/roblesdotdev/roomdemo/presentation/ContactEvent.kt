package com.roblesdotdev.roomdemo.presentation

import com.roblesdotdev.roomdemo.domain.model.Contact

sealed interface ContactEvent {
    data object SaveContact: ContactEvent
    data class DeleteContact(val contact: Contact) : ContactEvent
    data class SetFirstName(val firstName: String): ContactEvent
    data class SetLastName(val lastName: String): ContactEvent
    data class SetPhoneNumber(val phoneNumber: String): ContactEvent
    data class SetSortType(val sortType: SortType) : ContactEvent
    data object ShowDialog : ContactEvent
    data object HideDialog : ContactEvent
    data object Reset : ContactEvent
}