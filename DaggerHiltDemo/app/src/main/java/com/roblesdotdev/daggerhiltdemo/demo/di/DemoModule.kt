package com.roblesdotdev.daggerhiltdemo.demo.di

import com.roblesdotdev.daggerhiltdemo.demo.domain.usecase.GetMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DemoModule {
    @Provides
    @Singleton
    fun provideGetMessageUseCase(): GetMessageUseCase {
        return GetMessageUseCase()
    }
}