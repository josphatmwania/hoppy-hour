package com.josphatmwania.hoppyhour.di

import android.content.Context
import androidx.room.Room
import com.josphatmwania.hoppyhour.common.Constants
import com.josphatmwania.hoppyhour.common.SettingsDatastore
import com.josphatmwania.hoppyhour.feature_beer.data.datasource.local.HoppyHourDatabase
import com.josphatmwania.hoppyhour.feature_beer.data.datasource.remote.PunkApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext context: Context): SettingsDatastore {
        return SettingsDatastore(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(Constants.CALL_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providePunkApiService(retrofit: Retrofit): PunkApiService {
        return retrofit.create(PunkApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHoppyHourDatabase(@ApplicationContext context: Context): HoppyHourDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HoppyHourDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideHoppyHourDao(database: HoppyHourDatabase) = database.dao()
}