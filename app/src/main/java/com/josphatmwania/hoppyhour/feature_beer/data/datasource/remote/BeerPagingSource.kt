package com.josphatmwania.hoppyhour.feature_beer.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.josphatmwania.hoppyhour.common.Constants
import com.josphatmwania.hoppyhour.feature_beer.data.dto.toBeer
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer
import retrofit2.HttpException
import java.io.IOException

class BeerPagingSource(
    private val apiService: PunkApiService
) : PagingSource<Int, Beer>() {
    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition
    }

    override val keyReuseSupported: Boolean
        get() = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        val position = params.key ?: Constants.STARTING_POSITION

        return try {
            val beers = apiService.allBeers(position).map { it.toBeer() }
            LoadResult.Page(
                data = beers,
                prevKey = if (position == Constants.STARTING_POSITION) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}