package com.bapp.pointofsalesapp.feature_item.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Price(
    @PrimaryKey val priceID: Int ?= null,
    val itemName: String,
    val unitName: String,
    val price: Float,
    val priceRatio: Int
)
class InvalidPriceException(message: String): Exception(message)
