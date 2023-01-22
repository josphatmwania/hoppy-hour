package com.josphatmwania.hoppyhour.feature_beer.domain.model



data class Beer(
    val id: Int,
    val name: String,
    val description: String,
    val tagline: String,
    val ibu: Int,
    val image_url: String,
    val ingredients: Ingredients,
    val foodPairing: List<String>,
    val volume: Volume,
    val brewersTips: String,
)
