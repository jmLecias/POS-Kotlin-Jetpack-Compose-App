package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Category
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository

class AddCategory(
    private val repository: AppRepository
) {

    suspend operator fun invoke(category: Category) {
        return repository.insertCategory(category)
    }
}