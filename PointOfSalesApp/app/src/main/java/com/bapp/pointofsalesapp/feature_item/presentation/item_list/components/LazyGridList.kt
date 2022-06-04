package com.bapp.pointofsalesapp.feature_item.presentation.item_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bapp.pointofsalesapp.feature_item.domain.model.Item

@Composable
fun LazyGridRow(
    modifier: Modifier = Modifier,
    items: List<Item>,
    rows: Int = 1,
    cellSpacing: Dp = 2.dp,
    content: @Composable (Item) -> Unit
    ) {

    LazyRow{
        items(items.chunked(rows)) {column ->

            Column(Modifier.fillParentMaxHeight()) {
                for ((index, item) in column.withIndex()) {

                    Box(
                        Modifier.fillMaxHeight(1f / (rows - index))
                    ) {
                        content(item)
                    }
                    Spacer(modifier = Modifier.height(cellSpacing))
                }
            }

            Spacer(modifier = Modifier.width(cellSpacing))
        }
    }
}