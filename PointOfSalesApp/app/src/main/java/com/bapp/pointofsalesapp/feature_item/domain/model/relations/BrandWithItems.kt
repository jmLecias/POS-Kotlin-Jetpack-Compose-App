package com.bapp.pointofsalesapp.feature_item.domain.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.model.Item

data class BrandWithItems(
    @Embedded val brand: Brand,
    @Relation(
        parentColumn = "brandName",
        entityColumn = "brandName"
    )
    val items: List<Item>
)
