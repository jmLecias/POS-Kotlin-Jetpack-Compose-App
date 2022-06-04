package com.bapp.pointofsalesapp.feature_item.domain.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bapp.pointofsalesapp.feature_item.domain.model.SubUnit
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit

data class UnitWithSubUnits(
    @Embedded val unit: Unit,
    @Relation(
        parentColumn = "unitName",
        entityColumn = "unitName"
    )
    val subUnits: List<SubUnit>
)
