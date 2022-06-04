package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository

class AddBrand(
    private val repository: AppRepository
) {

    suspend operator fun invoke(brand: Brand) {
        return repository.insertBrand(brand)
    }
}