package com.josphatmwania.hoppyhour.feature_beer.domain.use_case

import com.josphatmwania.hoppyhour.common.Resource
import com.josphatmwania.hoppyhour.feature_beer.domain.repository.HoppyHourRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AllBeers @Inject constructor(
    private val repository: HoppyHourRepository
) {
    suspend operator fun invoke() = repository.allBears()
}