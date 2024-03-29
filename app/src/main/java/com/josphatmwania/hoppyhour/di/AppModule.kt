package com.josphatmwania.hoppyhour.di

import android.content.Context
import com.josphatmwania.hoppyhour.common.Constants
import com.josphatmwania.hoppyhour.common.SettingsDatastore
import com.josphatmwania.hoppyhour.common.UseCases
import com.josphatmwania.hoppyhour.feature_beer.data.datasource.remote.PunkApiService
import com.josphatmwania.hoppyhour.feature_beer.domain.repository.HoppyHourRepository
import com.josphatmwania.hoppyhour.feature_beer.domain.use_case.AllBeers
import com.josphatmwania.hoppyhour.feature_beer.domain.use_case.FindBeer
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
    fun provideALlBeersUseCase(repository: HoppyHourRepository) = AllBeers(repository)

    @Provides
    fun provideFindBeerUseCase(repository: HoppyHourRepository) = FindBeer(repository)

    @Provides
    fun provideUseCases(allBeers: AllBeers, findBeer: FindBeer): UseCases {
        return UseCases(
            allBeers = allBeers,
            findBeer = findBeer
        )
    }
}