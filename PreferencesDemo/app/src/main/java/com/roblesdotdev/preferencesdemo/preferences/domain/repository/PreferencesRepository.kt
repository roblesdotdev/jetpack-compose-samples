package com.roblesdotdev.preferencesdemo.preferences.domain.repository

interface PreferencesRepository {
    suspend fun storeInt(
        key: String,
        value: Int?
    )

    suspend fun getInt(
        key: String,
        defaultValue: Int?,
    ): Int?

    suspend fun storeBoolean(
        key: String,
        value: Boolean,
    )

    suspend fun getBoolean(
        key: String,
        defaultValue: Boolean
    ): Boolean
}