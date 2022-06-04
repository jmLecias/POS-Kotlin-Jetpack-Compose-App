package com.bapp.pointofsalesapp.feature_item.domain.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.model.Category

data class CategoryWithBrands(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "brandName",
        associateBy = Junction(BrandCategoryCrossRef::class)
    )
    val brands: List<Brand>
)
