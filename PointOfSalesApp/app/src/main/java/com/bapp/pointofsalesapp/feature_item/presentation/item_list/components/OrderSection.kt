package com.bapp.pointofsalesapp.feature_item.presentation.item_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bapp.pointofsalesapp.feature_item.domain.util.ItemOrder
import com.bapp.pointofsalesapp.feature_item.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    itemOrder: ItemOrder = ItemOrder.Name(OrderType.Ascending),
    onOrderChange: (ItemOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Name",
                selected = itemOrder is ItemOrder.Name,
                onSelect = { onOrderChange(ItemOrder.Name(itemOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Brand",
                selected = itemOrder is ItemOrder.Brand,
                onSelect = { onOrderChange(ItemOrder.Brand(itemOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Category",
                selected = itemOrder is ItemOrder.Category,
                onSelect = { onOrderChange(ItemOrder.Category(itemOrder.orderType)) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = itemOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(itemOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = itemOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(itemOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}