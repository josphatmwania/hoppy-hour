package com.josphatmwania.hoppyhour.feature_beer.data.datasource.local

import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer
import kotlinx.coroutines.flow.Flow

/**
 * This Repository provides insert, update, delete, and retrieve of [Beer] from a given data source.
 */

interface HoppyHourLocalRepository {


    /**
     * Get all of the Beers
     *
     */

    fun getAllBeers() : Flow<List<Beer>>
}