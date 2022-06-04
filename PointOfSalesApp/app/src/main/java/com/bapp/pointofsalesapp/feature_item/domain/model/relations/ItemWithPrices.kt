package com.bapp.pointofsalesapp.feature_item.domain.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.model.Price
import com.bapp.pointofsalesapp.feature_item.domain.model.SubUnit
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit

data class ItemWithPrices(
    @Embedded val item: Item,
    @Relation(
        parentColumn = "itemName",
        entityColumn = "itemName"
    )
    val prices: List<Price>
)
