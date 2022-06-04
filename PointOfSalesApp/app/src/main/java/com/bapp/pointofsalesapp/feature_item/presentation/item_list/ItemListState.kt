package com.bapp.pointofsalesapp.feature_item.presentation.item_list

import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.model.Category
import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.util.ItemOrder
import com.bapp.pointofsalesapp.feature_item.domain.util.OrderType

data class ItemListState(
    val items: List<Item> = emptyList(),
    val brands: List<Brand> = emptyList(),
    val categories: List<Category> = emptyList(),
    val itemOrder: ItemOrder = ItemOrder.Name(OrderType.Ascending),
    val isOrderSectionVisible: Boolean = false
)
