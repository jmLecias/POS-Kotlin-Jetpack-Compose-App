package com.bapp.pointofsalesapp.feature_item.presentation.item_list

import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.util.ItemOrder

sealed class ItemListEvent {
    data class OnOrderChange(val itemOrder: ItemOrder): ItemListEvent()
    data class OnItemClick(val item: Item): ItemListEvent()
    data class OnDeleteItemCLick(val item: Item): ItemListEvent()
    object OnAddItemClick: ItemListEvent()
    object OnUndoDeleteClick: ItemListEvent()
    object OnToggleOrderSectionClick: ItemListEvent()

}
