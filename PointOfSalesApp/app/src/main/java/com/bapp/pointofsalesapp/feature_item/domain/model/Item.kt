package com.bapp.pointofsalesapp.feature_item.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey val itemID: Int? = null,
    val itemName: String,
    val brandName: String,
    val categoryName: String,
    val unitName: String,
    val itemCost: Float
)

class InvalidItemException(message: String): Exception(message)
