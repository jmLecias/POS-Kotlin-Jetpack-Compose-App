package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Category
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCategories(
    private val repository: AppRepository
) {

    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategories().map { categories ->
            categories.sortedBy { it.categoryName.lowercase() }
        }
    }
}