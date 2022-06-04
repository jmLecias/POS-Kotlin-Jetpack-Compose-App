package com.bapp.pointofsalesapp.feature_item.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Brand(
    @PrimaryKey(autoGenerate = false)
    val brandName: String,
)
