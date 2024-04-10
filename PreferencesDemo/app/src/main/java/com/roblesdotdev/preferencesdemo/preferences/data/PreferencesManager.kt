package com.roblesdotdev.preferencesdemo.preferences.data

import com.roblesdotdev.preferencesdemo.preferences.domain.repository.PreferencesRepository

class PreferencesManager(
    private val preferences: PreferencesRepository
) {
    suspend fun getPreferredNumItems(): Int? {
        return preferences.getInt(
            key = NUM_ITEMS_TO_SHOW,
            defaultValue = DEFAULT_ITEMS_TO_SHOW
        )
    }

    suspend fun setPreferredNumItems(numItems: Int?) {
        preferences.storeInt(
            key = NUM_ITEMS_TO_SHOW,
            value = numItems,
        )
    }

    suspend fun getPreferredItemsEnabled(): Boolean  {
        return preferences.getBoolean(
            key = ITEMS_ENABLED_TO_EDIT,
            defaultValue = false
        )
    }

    suspend fun setPreferredItemsEnabled(enabled: Boolean) {
        preferences.storeBoolean(
            key = ITEMS_ENABLED_TO_EDIT,
            value = enabled,
        )
    }

    companion object {
        private const val DEFAULT_ITEMS_TO_SHOW = 15
        private const val NUM_ITEMS_TO_SHOW = "num_items_to_show"
        private const val ITEMS_ENABLED_TO_EDIT = "items_enabled_to_edit"
    }
}