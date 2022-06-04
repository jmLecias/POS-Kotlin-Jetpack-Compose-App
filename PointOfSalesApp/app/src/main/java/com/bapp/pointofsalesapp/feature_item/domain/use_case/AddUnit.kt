package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository

class AddUnit(
    private val repository: AppRepository
) {
    suspend operator fun invoke(unit: Unit) {
        return repository.insertUnit(unit)
    }
}