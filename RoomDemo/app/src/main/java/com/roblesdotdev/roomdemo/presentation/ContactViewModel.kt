package com.roblesdotdev.roomdemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roblesdotdev.roomdemo.domain.model.Contact
import com.roblesdotdev.roomdemo.domain.repository.ContactRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ContactViewModel(
    private val repo: ContactRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ContactState())
    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _contacts = _sortType.flatMapLatest { sortType ->
        when (sortType) {
            SortType.FIRST_NAME -> repo.getSortedContacts(sortType)
            SortType.LAST_NAME -> repo.getSortedContacts(sortType)
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun onEvent(event: ContactEvent) {
        when (event) {
            ContactEvent.HideDialog -> setDialog(false)
            ContactEvent.SaveContact -> saveContact()
            is ContactEvent.SetFirstName -> updateFirstName(event.firstName)
            is ContactEvent.SetLastName -> updateLastName(event.lastName)
            is ContactEvent.SetPhoneNumber -> updatePhoneNumber(event.phoneNumber)
            ContactEvent.ShowDialog -> setDialog(true)
            is ContactEvent.DeleteContact -> deleteContact(event.contact)
            ContactEvent.Reset -> reset()
            is ContactEvent.SetSortType -> updateSortType(event.sortType)
        }
    }

    private fun reset() {
        _state.update { prevState ->
            prevState.copy(firstName = "", lastName = "", phoneNumber = "", showDialog = false)
        }
    }

    private fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repo.deleteContact(contact)
        }
    }

    private fun saveContact() {
        if (!state.value.isValid) return
        viewModelScope.launch {
            val newContact = Contact(
                firstName = state.value.firstName,
                lastName = state.value.lastName,
                phoneNumber = state.value.phoneNumber
            )
            repo.upsertContact(newContact)
            reset()
        }
    }

    private fun updateFirstName(firstName: String) {
        _state.update { prevState -> prevState.copy(firstName = firstName) }
    }

    private fun updateLastName(lastName: String) {
        _state.update { prevState -> prevState.copy(lastName = lastName) }
    }

    private fun updatePhoneNumber(phoneNumber: String) {
        _state.update { prevState -> prevState.copy(phoneNumber = phoneNumber) }
    }

    private fun setDialog(value: Boolean) {
        _state.update { prevState -> prevState.copy(showDialog = value) }
    }

    private fun updateSortType(value: SortType) {
        _sortType.update { value }
    }

}