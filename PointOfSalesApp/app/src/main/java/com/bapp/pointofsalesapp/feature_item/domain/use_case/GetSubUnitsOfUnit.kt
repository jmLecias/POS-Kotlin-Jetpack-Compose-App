package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.model.SubUnit
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.UnitWithSubUnits
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import com.bapp.pointofsalesapp.feature_item.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSubUnitsOfUnit(
    private val repository: AppRepository
) {

    operator fun invoke(
        unitName: String
    ) : Flow<List<SubUnit>>? {
        return repository.getSubUnitsOfUnit(unitName).map { subUnits ->
           subUnits.sortedBy { it.subUnitName.lowercase() }
        }
    }
}