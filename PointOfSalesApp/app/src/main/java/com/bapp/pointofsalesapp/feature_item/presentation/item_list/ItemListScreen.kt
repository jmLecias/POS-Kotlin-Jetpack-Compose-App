package com.bapp.pointofsalesapp.feature_item.presentation.item_list

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bapp.pointofsalesapp.feature_item.domain.util.ItemOrder
import com.bapp.pointofsalesapp.feature_item.domain.util.UiEvent
import com.bapp.pointofsalesapp.feature_item.presentation.item_list.components.*
import kotlinx.coroutines.flow.collect

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun ItemListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ItemListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    val listState = rememberLazyListState()

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true, key2 = scaffoldState.snackbarHostState) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if(result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(ItemListEvent.OnUndoDeleteClick)
                    }
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                viewModel.onEvent(ItemListEvent.OnAddItemClick)
                    }
                ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Your Items",
                    style = MaterialTheme.typography.h4,
                    maxLines = 1
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(ItemListEvent.OnToggleOrderSectionClick)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort",
                        modifier = Modifier.scale(if(state.isOrderSectionVisible) -1f else 1f, 1f)
                    )
                }
            }
            
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    itemOrder = state.itemOrder,
                    onOrderChange = {
                        viewModel.onEvent(ItemListEvent.OnOrderChange(it))
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // Sorted by ItemName
                if (state.itemOrder::class == ItemOrder.Name::class) {
                    items(state.items) { item ->
                        AnimationBox(
                            enter = fadeIn(
                                animationSpec = tween(
                                    delayMillis = 100,
                                    durationMillis = 500
                                )
                            )
                        ) {
                            ItemTab(
                                item = item,
                                onEvent = viewModel::onEvent,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.onEvent(ItemListEvent.OnItemClick(item))
                                    }
                                    .background(
                                        color = Color.DarkGray,
                                        shape = RoundedCornerShape(18f)
                                    )
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }

                //Sorted by ItemBrand
                if (state.itemOrder::class == ItemOrder.Brand::class) {
                    items(state.brands) { brand->
                        AnimationBox(
                            enter = fadeIn(animationSpec = tween(delayMillis = 100)) +
                                    slideInHorizontally(animationSpec = tween(delayMillis = 100))
                        ) {
                            ItemGroupList(
                                label = brand.brandName
                            ) {
                                val itemsOfBrand = viewModel.getItemsOfBrand(
                                    brandName = brand.brandName,
                                    orderType = state.itemOrder.orderType
                                ).collectAsState(initial = emptyList())

                                LazyGridRow(
                                    items = itemsOfBrand.value,
                                    rows = 2,
                                    cellSpacing = 4.dp
                                ) { item ->
                                    AnimationBox(
                                        enter = fadeIn(animationSpec = tween(delayMillis = 150)) +
                                                slideInHorizontally(animationSpec = tween(delayMillis = 150))
                                    ) {
                                        ItemBox(
                                            size = 110.dp,
                                            cornerSize = 5.dp,
                                            item = item,
                                            onClick = {
                                                viewModel.onEvent(ItemListEvent.OnItemClick(item))
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                //Sorted by ItemCategory
                if (state.itemOrder::class == ItemOrder.Category::class) {
                    items(state.categories) { category->
                        AnimationBox(
                            enter = fadeIn(animationSpec = tween(delayMillis = 100)) +
                                    slideInHorizontally(animationSpec = tween(delayMillis = 100))
                        ) {
                            ItemGroupList(
                                label = category.categoryName
                            ) {
                                val itemsOfCategory = viewModel.getItemsOfCategory(
                                    categoryName = category.categoryName,
                                    orderType = state.itemOrder.orderType
                                ).collectAsState(initial = emptyList())

                                LazyGridRow(
                                    items = itemsOfCategory.value,
                                    rows = 2,
                                    cellSpacing = 4.dp
                                ) { item ->
                                    AnimationBox(
                                        enter = fadeIn(animationSpec = tween(delayMillis = 150)) +
                                                slideInHorizontally(animationSpec = tween(delayMillis = 150))
                                    ) {
                                        ItemBox(
                                            size = 110.dp,
                                            cornerSize = 5.dp,
                                            item = item,
                                            onClick = {
                                                viewModel.onEvent(ItemListEvent.OnItemClick(item))
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}