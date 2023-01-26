package com.josphatmwania.hoppyhour.feature_beer.domain.use_case

import com.josphatmwania.hoppyhour.common.Resource
import com.josphatmwania.hoppyhour.feature_beer.domain.repository.HoppyHourRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FindBeer @Inject constructor(
    private val repository: HoppyHourRepository
) {
    suspend operator fun invoke(id: Int) = flow {
        try {
            emit(Resource.Loading())
            val beer = repository.findBeer(id)
            emit(Resource.Success(beer))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}