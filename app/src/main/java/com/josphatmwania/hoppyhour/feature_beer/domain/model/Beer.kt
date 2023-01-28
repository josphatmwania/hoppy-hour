package com.josphatmwania.hoppyhour.feature_beer.domain.model



data class Beer(
    val id: Int,
    val name: String,
    val description: String,
    val tagline: String,
    val ibu: Double,
    val imageUrl: String,
    val ingredients: Ingredients,
    val foodPairing: List<String>,
    val volume: Volume,
    val brewersTips: String,
)
