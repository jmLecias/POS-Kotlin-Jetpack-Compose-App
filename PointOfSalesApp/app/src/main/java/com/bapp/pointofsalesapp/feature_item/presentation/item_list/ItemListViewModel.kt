package com.bapp.pointofsalesapp.feature_item.presentation.item_list

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.model.Price
import com.bapp.pointofsalesapp.feature_item.domain.use_case.ItemUseCases
import com.bapp.pointofsalesapp.feature_item.domain.util.ItemOrder
import com.bapp.pointofsalesapp.feature_item.domain.util.OrderType
import com.bapp.pointofsalesapp.feature_item.domain.util.Routes
import com.bapp.pointofsalesapp.feature_item.domain.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val itemUseCases: ItemUseCases
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = mutableStateOf(ItemListState())
    val state: State<ItemListState> = _state

    private var deletedItem: Item? = null

    private var getItemsJob: Job? = null


    init {
        getItems(ItemOrder.Name(OrderType.Ascending))

        getBrands()

        getCategories()
    }

    fun onEvent(event: ItemListEvent) {
        when(event) {
            is ItemListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_ITEM + "?itemID=${event.item.itemID}"))
            }
            is ItemListEvent.OnDeleteItemCLick -> {
                viewModelScope.launch {
                    deletedItem = event.item
                    itemUseCases.deleteItem(event.item)
                    sendUiEvent(
                        UiEvent.ShowSnackBar(
                        message = "Item deleted",
                        action = "Undo"
                    ))
                }
            }
            is ItemListEvent.OnAddItemClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_ITEM))
            }
            is ItemListEvent.OnUndoDeleteClick -> {
                deletedItem?.let {item ->
                    viewModelScope.launch {
                        itemUseCases.addItem(item)
                    }
                }
            }
            is ItemListEvent.OnOrderChange -> {
                if (state.value.itemOrder::class == event.itemOrder::class &&
                    state.value.itemOrder.orderType == event.itemOrder.orderType
                ) {
                    return
                }
                getItems(event.itemOrder)
            }
            is ItemListEvent.OnToggleOrderSectionClick -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private fun getItems(itemOrder: ItemOrder) {
        getItemsJob?.cancel()
        getItemsJob = itemUseCases.getItems(itemOrder)
            .onEach { items ->
                _state.value = state.value.copy(
                    items = items,
                    itemOrder = itemOrder
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getBrands() {
        itemUseCases.getBrands()
            .onEach { brands ->
                _state.value = state.value.copy(
                    brands = brands
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getCategories() {
        itemUseCases.getCategories()
            .onEach { categories ->
                _state.value = state.value.copy(
                    categories = categories
                )
            }
            .launchIn(viewModelScope)
    }

    fun getItemsOfBrand(brandName: String, orderType: OrderType): Flow<List<Item>> {
       return itemUseCases.getItemsOfBrand(
            brandName = brandName,
            orderType = orderType
        )
    }

    fun getItemsOfCategory(categoryName: String, orderType: OrderType): Flow<List<Item>> {
        return itemUseCases.getItemsOfCategory(
            categoryName = categoryName,
            orderType = orderType
        )
    }
}