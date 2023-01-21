package com.josphatmwania.hoppyhour.feature_beer.data.dto

data class IngredientsDto(
    val hops: List<HopDto>,
    val malt: List<MaltDto>,
    val yeast: String
)