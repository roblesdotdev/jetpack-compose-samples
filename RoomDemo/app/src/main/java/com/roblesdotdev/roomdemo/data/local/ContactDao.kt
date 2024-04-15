package com.roblesdotdev.roomdemo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.roblesdotdev.roomdemo.data.local.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: ContactEntity)

    @Query("DELETE FROM contact_demo WHERE id = :contactId")
    suspend fun deleteContact(contactId: Int)

    @Query("SELECT * FROM contact_demo ORDER BY firstName ASC")
    fun getContactOrderedByFirstName(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contact_demo ORDER BY lastName ASC")
    fun getContactOrderedByLastName(): Flow<List<ContactEntity>>
}
