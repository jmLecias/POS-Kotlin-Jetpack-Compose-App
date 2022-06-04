package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import com.bapp.pointofsalesapp.feature_item.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetItemsOfCategory(
    private val repository: AppRepository
) {

    operator fun invoke(
        categoryName: String,
        orderType: OrderType = OrderType.Ascending
    ): Flow<List<Item>> {
        return repository.getItemsOfCategory(categoryName).map { items ->
            when(orderType) {
                is OrderType.Ascending -> {
                    items.sortedBy { it.itemName.lowercase() }
                }
                is OrderType.Descending -> {
                    items.sortedByDescending{ it.itemName.lowercase() }
                }
            }
        }
    }
}