package com.roblesdotdev.roomdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roblesdotdev.roomdemo.data.local.entity.ContactEntity

@Database(
    entities = [ContactEntity::class],
    version = 1,
)
abstract  class ContactDatabase: RoomDatabase() {
    abstract val dao: ContactDao
}