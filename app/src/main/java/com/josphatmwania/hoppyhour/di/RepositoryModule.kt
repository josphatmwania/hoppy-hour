package com.josphatmwania.hoppyhour.di

import com.josphatmwania.hoppyhour.feature_beer.data.repository.HoppyHourRepositoryImpl
import com.josphatmwania.hoppyhour.feature_beer.domain.repository.HoppyHourRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: HoppyHourRepositoryImpl): HoppyHourRepository

}