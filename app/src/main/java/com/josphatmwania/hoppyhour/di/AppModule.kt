package com.josphatmwania.hoppyhour.di

import android.content.Context
import com.josphatmwania.hoppyhour.common.SettingsDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext context: Context): SettingsDatastore {
        return SettingsDatastore(context)
    }

}