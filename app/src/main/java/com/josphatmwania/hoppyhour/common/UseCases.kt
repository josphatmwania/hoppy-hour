package com.josphatmwania.hoppyhour.common

import com.josphatmwania.hoppyhour.feature_beer.domain.use_case.AllBeers
import com.josphatmwania.hoppyhour.feature_beer.domain.use_case.FindBeer

data class UseCases(
    val allBeers: AllBeers,
    val findBeer: FindBeer
)
