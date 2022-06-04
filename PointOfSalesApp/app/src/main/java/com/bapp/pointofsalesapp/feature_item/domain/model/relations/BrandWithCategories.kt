package com.bapp.pointofsalesapp.feature_item.domain.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.model.Category

data class BrandWithCategories(
    @Embedded val brand: Brand,
    @Relation(
        parentColumn = "brandName",
        entityColumn = "categoryName",
        associateBy = Junction(BrandCategoryCrossRef::class)
    )
    val categories: List<Category>
)
