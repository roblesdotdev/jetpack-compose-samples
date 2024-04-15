package com.roblesdotdev.roomdemo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.roblesdotdev.roomdemo.domain.model.Contact

@Entity("contact_demo")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)

fun ContactEntity.toDomain(): Contact {
    return Contact(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        phoneNumber = this.phoneNumber,
    )
}

fun Contact.toEntity(): ContactEntity {
    return ContactEntity(
        firstName = this.firstName,
        lastName = this.lastName,
        phoneNumber = this.phoneNumber
    )
}