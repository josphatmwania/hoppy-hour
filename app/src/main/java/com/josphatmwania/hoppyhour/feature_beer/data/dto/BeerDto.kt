package com.josphatmwania.hoppyhour.feature_beer.data.dto

import com.google.gson.annotations.SerializedName

data class BeerDto(
    val abv: Double,
    @SerializedName("attenuation_level")
    val attenuationLevel: Int,
    @SerializedName("boil_volume")
    val boilVolume: BoilVolumeDto,
    @SerializedName("brewers_tips")
    val brewersTips: String,
    @SerializedName("contributed_by")
    val contributedBy: String,
    val description: String,
    val ebc: Int,
    @SerializedName("first_brewed")
    val firstBrewed: String,
    @SerializedName("food_pairing")
    val foodPairing: List<String>,
    val ibu: Int,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val ingredients: IngredientsDto,
    val method: MethodDto,
    val name: String,
    val ph: Double,
    val srm: Int,
    val tagline: String,
    @SerializedName("target_fg")
    val targetFg: Int,
    @SerializedName("target_og")
    val targetOg: Int,
    val volume: VolumeDto
)

