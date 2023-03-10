package com.josphatmwania.hoppyhour.feature_beer.domain.repository

import androidx.paging.PagingData
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer
import kotlinx.coroutines.flow.Flow

interface HoppyHourRepository {
    suspend fun allBears(): Flow<PagingData<Beer>>
    suspend fun findBeer(id: Int): Beer
}