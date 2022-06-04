package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository

class GetItemById(
    private val repository: AppRepository
) {

    suspend operator fun invoke(id: Int): Item? {
        return repository.getItemByID(id)
    }
}