package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.model.Price
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import com.bapp.pointofsalesapp.feature_item.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPricesOfItem(
    private val repository: AppRepository
) {

    operator fun invoke(itemName: String): Flow<List<Price>> {
        return repository.getPricesOfItem(itemName).map { prices ->
           prices.sortedBy { it.priceRatio }
        }
    }
}