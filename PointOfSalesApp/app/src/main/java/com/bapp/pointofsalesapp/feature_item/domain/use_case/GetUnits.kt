package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUnits(
    private val repository: AppRepository
) {

    operator fun invoke(): Flow<List<Unit>> {
        return repository.getUnits().map { units ->
            units.sortedBy { it.unitName.lowercase() }
        }
    }
}