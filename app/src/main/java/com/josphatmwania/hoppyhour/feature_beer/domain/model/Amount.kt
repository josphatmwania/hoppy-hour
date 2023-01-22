package com.josphatmwania.hoppyhour.feature_beer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Amount(
    val unit: String,
    val value: Double
)
