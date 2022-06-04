package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository

class GetUnitByName(
    private val repository: AppRepository
) {

    suspend operator fun invoke(unitName: String): Unit? {
        return repository.getUnitByName(unitName)
    }
}