package com.josphatmwania.hoppyhour.feature_beer.data.dto

import com.josphatmwania.hoppyhour.feature_beer.domain.model.Malt

data class MaltDto(
    val amount: AmountDto,
    val name: String
)

fun MaltDto.toMalt(): Malt {
    return Malt(
        amount = amount.toAmount(),
        name = name
    )
}