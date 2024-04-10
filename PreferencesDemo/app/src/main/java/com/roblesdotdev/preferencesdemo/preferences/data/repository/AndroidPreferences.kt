package com.roblesdotdev.preferencesdemo.preferences.data.repository

import android.content.SharedPreferences
import com.roblesdotdev.preferencesdemo.preferences.domain.repository.PreferencesRepository

class AndroidPreferences(
    private val sharedPreferences: SharedPreferences
) : PreferencesRepository {
    override suspend fun storeInt(key: String, value: Int?) {
        if (value != null) {
            sharedPreferences
                .edit()
                .putInt(key, value)
                .apply()
        } else {
            sharedPreferences
                .edit()
                .remove(key)
                .apply()
        }
    }

    override suspend fun getInt(key: String, defaultValue: Int?): Int? {
        return if(sharedPreferences.contains(key))  {
            sharedPreferences.getInt(key, 0)
        } else {
            defaultValue
        }
    }

    override suspend fun storeBoolean(key: String, value: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    override suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

}