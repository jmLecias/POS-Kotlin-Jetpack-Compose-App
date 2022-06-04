package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.InvalidItemException
import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import kotlin.jvm.Throws

class AddItem(
    private val repository: AppRepository
) {
    @Throws(InvalidItemException::class)
    suspend operator fun invoke(item: Item) {
        
        if(item.itemName.isBlank()) {
            throw InvalidItemException("The name of the item can't be empty.")
        }
        if(item.brandName.isBlank()) {
            throw InvalidItemException("The brand of the item can't be empty.")
        }
        if(item.categoryName.isBlank()) {
            throw InvalidItemException("The category of the item can't be empty.")
        }
        if(item.unitName.isBlank()) {
            throw InvalidItemException("The unit of the item can't be empty.")
        }
        repository.insertItem(item)
    }
}