package com.josphatmwania.hoppyhour.feature_beer.data.dto

import com.google.gson.annotations.SerializedName
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer

data class BeerDto(
    val abv: Double,
    @SerializedName("attenuation_level")
    val attenuationLevel: Double,
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
    val ibu: Double,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val ingredients: IngredientsDto,
    val method: MethodDto,
    val name: String,
    val ph: Double,
    val srm: Double,
    val tagline: String,
    @SerializedName("target_fg")
    val targetFg: Double,
    @SerializedName("target_og")
    val targetOg: Double,
    val volume: VolumeDto
)

fun BeerDto.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        description = description,
        tagline = tagline,
        ibu = ibu,
        imageUrl = imageUrl,
        ingredients = ingredients.toIngredients(),
        foodPairing = foodPairing,
        volume = volume.toVolume(),
        brewersTips = brewersTips
    )
}