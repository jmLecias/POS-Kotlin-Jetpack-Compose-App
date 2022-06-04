package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import com.bapp.pointofsalesapp.feature_item.domain.util.ItemOrder
import com.bapp.pointofsalesapp.feature_item.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetItems(
    private val repository: AppRepository
) {

    operator fun invoke(
        itemOrder: ItemOrder = ItemOrder.Name(OrderType.Ascending)
    ): Flow<List<Item>> {
        return repository.getItems().map { items ->
            when(itemOrder.orderType) {
                is OrderType.Ascending -> {
                    when(itemOrder) {
                        is ItemOrder.Name -> items.sortedBy { it.itemName.lowercase() }
                        is ItemOrder.Brand -> items.sortedBy { it.brandName.lowercase() }
                        is ItemOrder.Category -> items.sortedBy { it.categoryName.lowercase() }
                    }
                }
                is OrderType.Descending-> {
                    when(itemOrder) {
                        is ItemOrder.Name -> items.sortedByDescending { it.itemName.lowercase() }
                        is ItemOrder.Brand -> items.sortedByDescending { it.brandName.lowercase() }
                        is ItemOrder.Category -> items.sortedByDescending { it.categoryName.lowercase() }
                    }
                }
            }
        }
    }
}