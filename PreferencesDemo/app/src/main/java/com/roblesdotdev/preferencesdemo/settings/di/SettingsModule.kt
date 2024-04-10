package com.roblesdotdev.preferencesdemo.settings.di

import android.content.Context
import com.roblesdotdev.preferencesdemo.preferences.data.PreferencesManager
import com.roblesdotdev.preferencesdemo.preferences.data.repository.AndroidPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun providesPreferencesManager(@ApplicationContext context: Context): PreferencesManager {
        val sharedPreferences = context.getSharedPreferences("demo_preferences", Context.MODE_PRIVATE)
        val androidPreferences = AndroidPreferences(sharedPreferences = sharedPreferences)
        return PreferencesManager(preferences = androidPreferences)
    }
}