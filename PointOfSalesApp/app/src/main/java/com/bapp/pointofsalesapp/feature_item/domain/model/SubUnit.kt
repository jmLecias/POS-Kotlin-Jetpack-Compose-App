package com.bapp.pointofsalesapp.feature_item.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubUnit(
    @PrimaryKey(autoGenerate = false)
    val subUnitName: String,
    val subUnitAbbreviation: String,
    val unitName: String
)
