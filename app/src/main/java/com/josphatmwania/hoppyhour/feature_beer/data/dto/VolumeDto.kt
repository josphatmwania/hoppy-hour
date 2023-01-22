package com.josphatmwania.hoppyhour.feature_beer.data.dto

import com.josphatmwania.hoppyhour.feature_beer.domain.model.Volume

data class VolumeDto(
    val unit: String,
    val value: Int
)

fun VolumeDto.toVolume(): Volume {
    return Volume(
        unit = unit,
        value = value
    )
}