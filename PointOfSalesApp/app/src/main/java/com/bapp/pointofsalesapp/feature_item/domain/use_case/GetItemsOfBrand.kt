package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import com.bapp.pointofsalesapp.feature_item.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetItemsOfBrand(
    private val repository: AppRepository
) {

    operator fun invoke(
        brandName: String,
        orderType: OrderType = OrderType.Ascending
    ): Flow<List<Item>> {
        return repository.getItemsOfBrand(brandName).map { items ->
            when(orderType) {
                is OrderType.Ascending -> {
                    items.sortedBy { it.itemName.lowercase() }
                }
                is OrderType.Descending -> {
                    items.sortedByDescending { it.itemName.lowercase() }
                }
            }
        }
    }
}