package com.josphatmwania.hoppyhour.feature_beer.data.datasource.remote

import com.josphatmwania.hoppyhour.feature_beer.data.dto.BeerDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PunkApiService {
    @GET("beers")
    suspend fun allBeers(@Query("page") page: Int): List<BeerDto>

    @GET("beers/{id}")
    suspend fun findBeer(@Path("id") id: Int): List<BeerDto>
}