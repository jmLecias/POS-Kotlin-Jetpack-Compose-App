package com.bapp.pointofsalesapp.feature_item.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Unit(
    @PrimaryKey(autoGenerate = false)
    val unitName: String,
    val unitAbbreviation: String,
)
class InvalidUnitException(message: String): Exception(message)
