package com.josphatmwania.hoppyhour.feature_beer.data.dto

import com.josphatmwania.hoppyhour.feature_beer.domain.model.Ingredients

data class IngredientsDto(
    val hops: List<HopDto>,
    val malt: List<MaltDto>,
    val yeast: String
)

fun IngredientsDto.toIngredients(): Ingredients {
    return Ingredients(
        hops = hops.map { it.toHop() },
        malt = malt.map { it.toMalt() },
        yeast = yeast
    )
}