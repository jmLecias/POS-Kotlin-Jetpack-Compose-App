package com.bapp.pointofsalesapp.feature_item.domain.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bapp.pointofsalesapp.feature_item.domain.model.Category
import com.bapp.pointofsalesapp.feature_item.domain.model.Item

data class CategoryWithItems(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "categoryName"
    )
    val items: List<Item>
)
