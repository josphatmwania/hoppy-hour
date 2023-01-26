package com.josphatmwania.hoppyhour.feature_beer.presentation.events

import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer

data class BeerEvent(
    var data: Beer? = null,
    var isLoading: Boolean = false,
    var message: String = ""
)
