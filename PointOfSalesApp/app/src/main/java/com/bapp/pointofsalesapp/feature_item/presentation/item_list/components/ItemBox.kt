package com.bapp.pointofsalesapp.feature_item.presentation.item_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bapp.pointofsalesapp.feature_item.domain.model.Item

@Composable
fun ItemBox(
    size: Dp,
    cornerSize: Dp,
    item: Item,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .height(size)
            .width(size)
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(cornerSize)
            )
            .clickable { onClick() }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                imageVector = Icons.Default.Image,
                contentDescription = "No Image",
                modifier = Modifier
                    .size(size/4)
                    .align(Alignment.Center),
                alpha = 0.2f
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = item.itemName,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}