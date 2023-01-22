package com.josphatmwania.hoppyhour.feature_beer.domain.model


data class Ingredients (
    val hops: List<Hop>,
    val malt: List<Malt>,
    val yeast: String
)