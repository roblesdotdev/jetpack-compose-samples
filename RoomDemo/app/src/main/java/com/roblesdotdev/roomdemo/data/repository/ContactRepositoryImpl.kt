package com.roblesdotdev.roomdemo.data.repository

import com.roblesdotdev.roomdemo.data.local.ContactDao
import com.roblesdotdev.roomdemo.data.local.entity.toDomain
import com.roblesdotdev.roomdemo.data.local.entity.toEntity
import com.roblesdotdev.roomdemo.domain.model.Contact
import com.roblesdotdev.roomdemo.domain.repository.ContactRepository
import com.roblesdotdev.roomdemo.presentation.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactRepositoryImpl(
    private val dao: ContactDao
) : ContactRepository {
    override suspend fun upsertContact(contact: Contact) {
        dao.upsertContact(contact.toEntity())
    }

    override suspend fun deleteContact(contact: Contact) {
        dao.deleteContact(contact.id)
    }

    override suspend fun getSortedContacts(orderBy: SortType): Flow<List<Contact>> {
        val result = when (orderBy) {
            SortType.FIRST_NAME -> dao.getContactOrderedByFirstName()
            SortType.LAST_NAME -> dao.getContactOrderedByLastName()
        }
        return result.map { contactList ->
            contactList.map { it.toDomain() }
        }
    }

}