package com.roblesdotdev.roomdemo.domain.repository

import com.roblesdotdev.roomdemo.domain.model.Contact
import com.roblesdotdev.roomdemo.presentation.SortType
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun upsertContact(contact: Contact)
    suspend fun deleteContact(contact: Contact)
    suspend fun getSortedContacts(orderBy: SortType): Flow<List<Contact>>
}