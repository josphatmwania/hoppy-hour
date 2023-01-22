package com.josphatmwania.hoppyhour.feature_beer.data.dto

import com.google.gson.annotations.SerializedName

data class MethodDto(
    val fermentation: FermentationDto,
    @SerializedName("mash_temp")
    val mashTemp: List<MashTempDto>,
    val twist: String
)