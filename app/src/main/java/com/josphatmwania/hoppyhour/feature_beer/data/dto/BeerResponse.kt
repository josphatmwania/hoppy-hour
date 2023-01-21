package com.josphatmwania.hoppyhour.feature_beer.data.dto

data class BeerResponse(
    val abv: Double,
    val attenuation_level: Int,
    val boil_volume: BoilVolumeDto,
    val brewers_tips: String,
    val contributed_by: String,
    val description: String,
    val ebc: Int,
    val first_brewed: String,
    val food_pairing: List<String>,
    val ibu: Int,
    val id: Int,
    val image_url: String,
    val ingredients: IngredientsDto,
    val method: MethodDto,
    val name: String,
    val ph: Double,
    val srm: Int,
    val tagline: String,
    val target_fg: Int,
    val target_og: Int,
    val volume: VolumeDto
)