package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.relations.BrandCategoryCrossRef
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository

class AddBrandCategoryCrossRef(
    private val repository: AppRepository
) {

    suspend operator fun invoke(brandCategoryCrossRef: BrandCategoryCrossRef) {
        return repository.insertBrandCategoryCrossRef(brandCategoryCrossRef)
    }
}