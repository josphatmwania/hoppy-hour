package com.josphatmwania.hoppyhour.feature_beer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.josphatmwania.hoppyhour.common.Constants
import com.josphatmwania.hoppyhour.feature_beer.data.datasource.remote.BeerPagingSource
import com.josphatmwania.hoppyhour.feature_beer.data.datasource.remote.PunkApiService
import com.josphatmwania.hoppyhour.feature_beer.data.dto.toBeer
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer
import com.josphatmwania.hoppyhour.feature_beer.domain.repository.HoppyHourRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class HoppyHourRepositoryImpl @Inject constructor(
    private val apiService: PunkApiService
) : HoppyHourRepository {
    override suspend fun allBears(page: Int): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { BeerPagingSource(apiService) }
        ).flow
    }

    override suspend fun findBeer(id: Int): Flow<Beer> {
        return apiService.findBeer(id).map { it.toBeer() }.asFlow()
    }
}