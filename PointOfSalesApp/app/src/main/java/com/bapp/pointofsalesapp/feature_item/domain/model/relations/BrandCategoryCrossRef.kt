package com.bapp.pointofsalesapp.feature_item.domain.model.relations

import androidx.room.Entity

@Entity(primaryKeys = ["brandName", "categoryName"])
data class BrandCategoryCrossRef(
    val brandName: String,
    val categoryName: String,
)
