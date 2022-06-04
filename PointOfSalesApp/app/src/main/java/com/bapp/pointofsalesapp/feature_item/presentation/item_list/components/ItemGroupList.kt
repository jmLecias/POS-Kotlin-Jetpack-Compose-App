package com.bapp.pointofsalesapp.feature_item.presentation.item_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ItemGroupList(
    modifier: Modifier = Modifier,
    label: String,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column{
            Text(
                text = label,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box{
                content()
            }
        }
    }
}