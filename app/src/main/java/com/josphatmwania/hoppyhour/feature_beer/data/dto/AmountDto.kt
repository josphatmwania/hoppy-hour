package com.josphatmwania.hoppyhour.feature_beer.data.dto

import com.josphatmwania.hoppyhour.feature_beer.domain.model.Amount

data class AmountDto(
    val unit: String,
    val value: Double
)

fun AmountDto.toAmount(): Amount {
    return Amount(
        unit = unit,
        value = value
    )
}