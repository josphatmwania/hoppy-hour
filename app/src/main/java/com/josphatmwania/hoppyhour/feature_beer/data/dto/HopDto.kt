package com.josphatmwania.hoppyhour.feature_beer.data.dto

import com.josphatmwania.hoppyhour.feature_beer.domain.model.Hop

data class HopDto(
    val add: String,
    val amount: AmountDto,
    val attribute: String,
    val name: String
)

fun HopDto.toHop(): Hop {
    return Hop(
        add = add,
        amount = amount.toAmount(),
        attribute = attribute,
        name = name
    )
}