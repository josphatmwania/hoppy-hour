package com.josphatmwania.hoppyhour.feature_beer.data.dto

data class MethodDto(
    val fermentation: FermentationDto,
    val mash_temp: List<MashTempDto>,
    val twist: String
)