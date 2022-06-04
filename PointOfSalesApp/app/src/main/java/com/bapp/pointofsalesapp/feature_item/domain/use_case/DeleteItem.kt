package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository

class DeleteItem(
    private val repository: AppRepository
) {

    suspend operator fun invoke(item: Item) {
        repository.deleteItem(item)
    }
}