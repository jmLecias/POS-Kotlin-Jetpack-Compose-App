package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.InvalidPriceException
import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.model.Price
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import kotlin.jvm.Throws

class AddPrice(
    private val repository: AppRepository
) {
    @Throws(InvalidPriceException::class)
    suspend operator fun invoke(price: Price) {

        if(price.price == 0f) {
            throw InvalidPriceException("Item prices can't be empty")
        }
        if(price.priceRatio == 0) {
            throw InvalidPriceException("A price ratio can't be empty")
        }
        repository.insertPrice(price)
    }
}