package com.bapp.pointofsalesapp.feature_item.presentation.item_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.presentation.item_list.ItemListEvent

@Composable
fun ItemTab(
    item: Item,
    onEvent: (ItemListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.itemName,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.categoryName,
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.brandName,
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        IconButton(
            onClick = {
                onEvent(ItemListEvent.OnDeleteItemCLick(item))
            },
            modifier = Modifier.align(Alignment.BottomEnd)

        ) {
            Icon(
                modifier = Modifier.size(17.dp),
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete")
        }

    }

}