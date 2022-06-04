package com.bapp.pointofsalesapp.feature_item.presentation.add_edit_item

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bapp.pointofsalesapp.feature_item.domain.model.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.BrandCategoryCrossRef
import com.bapp.pointofsalesapp.feature_item.domain.use_case.ItemUseCases
import com.bapp.pointofsalesapp.feature_item.domain.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.util.*
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class AddEditItemViewModel @Inject constructor(
    private val itemUseCases: ItemUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(AddEditItemDetailsState())
    val state: State<AddEditItemDetailsState> = _state

    val itemPricesListState = mutableStateListOf(mutableStateOf(PriceState()))

//    val pricesStateList = flow {
//        state.value.pricesOfItem
//            .forEach { price ->
//                val priceState = mutableStateOf(
//                    PriceState(
//                        unitName = price.unitName,
//                        unitAbbreviation = itemUseCases
//                            .getUnitByName(price.unitName)!!.unitAbbreviation,
//                        price = price,
//                        itemPrice = price.price.toString(),
//                        priceRatio = price.priceRatio.toString()
//                    )
//                )
//                emit(priceState)
//            }
//    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val time = measureTimeMillis { getValues() }
        val itemId = savedStateHandle.get<Int>("itemID")!!
        if (itemId != -1) {
           viewModelScope.launch {
               delay(timeMillis = time)
               itemUseCases.getItemById(itemId)?.let { item ->
                   this@AddEditItemViewModel._state.value =
                       state.value.copy(
                           item = item,
                           headerText = "Edit Item",
                           itemName = item.itemName,
                           itemBrand = item.brandName,
                           itemCategory = item.categoryName,
                           itemUnit = item.unitName,
                           itemUnitAbbreviation = itemUseCases
                               .getUnitByName(item.unitName)!!.unitAbbreviation,
                           itemCost = item.itemCost.toString()
                       )
               }
               this@AddEditItemViewModel.getPricesOfItem()
               this@AddEditItemViewModel.itemPricesListState.clear()
               this@AddEditItemViewModel.state.value.pricesOfItem.forEach { price ->
                   this@AddEditItemViewModel.itemPricesListState.add(
                       mutableStateOf(
                           PriceState(
                               unitName = price.unitName,
                               unitAbbreviation = itemUseCases
                                   .getUnitByName(price.unitName)!!.unitAbbreviation,
                               price = price,
                               itemPrice = price.price.toString(),
                               priceRatio = price.priceRatio.toString()
                           )
                       )
                   )
               }
           }
        }
    }

    fun onEvent(event: AddEditItemEvent) {
        when(event) {
            is AddEditItemEvent.OnItemNameChange -> {
                _state.value = state.value.copy(
                    itemName = event.itemName
                )
            }
            is AddEditItemEvent.OnBrandNameChange -> {
                _state.value = state.value.copy(
                    itemBrand = event.brandName
                )
            }
            is AddEditItemEvent.OnCategoryNameChange -> {
                _state.value = state.value.copy(
                    itemCategory = event.categoryName
                )
            }
            is AddEditItemEvent.OnItemCostChange -> {
                _state.value = state.value.copy(
                    itemCost = event.itemCost
                )
            }
            is AddEditItemEvent.OnUnitDropdownClick -> {
                this.getSubUnitsOfUnit(event.unit.unitName)
                itemPricesListState.clear()
                itemPricesListState.add(
                    mutableStateOf(
                        PriceState(
                            unitName = event.unit.unitName,
                            unitAbbreviation = event.unit.unitAbbreviation,
                            priceRatio = "1"
                        )
                    )
                )
            }
            is AddEditItemEvent.OnUnitNameChange -> {
                _state.value = state.value.copy(
                    itemUnit = event.unitName
                )
            }
            is AddEditItemEvent.OnUnitAbbreviationChange -> {
                _state.value = state.value.copy(
                    itemUnitAbbreviation = event.unitAbbreviation
                )
            }
            is AddEditItemEvent.OnAddPriceClick -> {
                itemPricesListState.add(mutableStateOf(PriceState()))
            }
            is AddEditItemEvent.OnUndoAddPriceClick -> {
                if (itemPricesListState.lastIndex > 0) {
                    itemPricesListState.removeAt(itemPricesListState.lastIndex)
                }
            }
            is AddEditItemEvent.OnSaveItemClick -> {
                viewModelScope.launch {

                    // add item details
                    try {
                        itemUseCases.addItem(
                            Item(
                                itemID = state.value.item?.itemID,
                                itemName = state.value.itemName,
                                brandName = state.value.itemBrand,
                                categoryName = state.value.itemCategory,
                                unitName = state.value.itemUnit,
                                itemCost = state.value.itemCost.toFloat()
                            )
                        )
                        itemUseCases.addBrand(
                            Brand(
                                brandName = state.value.itemBrand
                            )
                        )
                        itemUseCases.addCategory(
                            Category(
                                categoryName = state.value.itemCategory
                            )
                        )
                        itemUseCases.addBrandCategoryCrossRef(
                            BrandCategoryCrossRef(
                                brandName = state.value.itemBrand,
                                categoryName = state.value.itemCategory
                            )
                        )
                        itemUseCases.addUnit(
                            Unit(
                                unitName = state.value.itemUnit,
                                unitAbbreviation = state.value.itemUnitAbbreviation
                            )
                        )
                    } catch (e: InvalidItemException) {
                        sendUiEvent(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save item"
                            )
                        )
                        return@launch
                    }
                    // add units, sub units, and prices
//                    try {
//                        if(itemPricesListState.isNotEmpty()) {
//                            try {
//                                itemPricesListState.forEachIndexed { index, _priceState ->
//                                    val priceState: State<PriceState> = _priceState
//
//                                    itemUseCases.addUnit(
//                                        Unit(
//                                            unitName = priceState.value.unitName,
//                                            unitAbbreviation = priceState.value.unitAbbreviation
//                                        )
//                                    )
//
//                                    if (index > 0) {
//                                        itemUseCases.addSubUnit(
//                                            SubUnit(
//                                                subUnitName = priceState.value.unitName,
//                                                subUnitAbbreviation = priceState.value.unitAbbreviation,
//                                                unitName = state.value.itemUnit
//                                            )
//                                        )
//                                    }
//                                }
//                            } catch (e: InvalidUnitException) {
//                                sendUiEvent(
//                                    UiEvent.ShowSnackBar(
//                                        message = e.message ?: "Couldn't save item"
//                                    )
//                                )
//                                return@launch
//                            }
//
//
//                            try {
//                                itemPricesListState.forEach { _priceState ->
//                                    val priceState: State<PriceState> = _priceState
//
//                                    itemUseCases.addPrice(
//                                        Price(
//                                            priceID = priceState.value.price?.priceID,
//                                            itemName = state.value.itemName,
//                                            unitName = priceState.value.unitName,
//                                            price = priceState.value.itemPrice.toFloat(),
//                                            priceRatio = priceState.value.priceRatio.toInt()
//                                        )
//                                    )
//                                }
//                            } catch (e: InvalidPriceException) {
//                                sendUiEvent(
//                                    UiEvent.ShowSnackBar(
//                                        message = e.message ?: "Couldn't save item"
//                                    )
//                                )
//                                return@launch
//                            }
//                        }
//                    } catch (e: Exception) {
//                        sendUiEvent(
//                            UiEvent.ShowSnackBar(
//                                message = e.message ?: "Couldn't save item"
//                            )
//                        )
//                        return@launch
//                    }

                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private fun getPricesOfItem() {
        itemUseCases.getPricesOfItem(state.value.item!!.itemName)
            .onEach { prices ->
                _state.value = state.value.copy(
                    pricesOfItem = prices
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getPriceStateCollection(prices: List<Price>): SnapshotStateList<MutableState<PriceState>> {
        val priceStateCollection = mutableStateListOf(mutableStateOf(PriceState()))
        viewModelScope.launch {
            prices.onEach { price ->
                priceStateCollection.add(
                    mutableStateOf(
                        PriceState(
                            unitName = price.unitName,
                            unitAbbreviation = itemUseCases
                                .getUnitByName(price.unitName)!!.unitAbbreviation,
                            price = price,
                            itemPrice = price.price.toString(),
                            priceRatio = price.priceRatio.toString()
                        )
                    )
                )
            }
        }
        return priceStateCollection
    }


    private fun getSubUnitsOfUnit(unitName: String) {
        itemUseCases.getSubUnitsOfUnit(unitName)
        ?.onEach { subUnits ->
            _state.value = state.value.copy(
                subUnitsOfCurrentUnit = subUnits
            )
        }?.launchIn(viewModelScope)
    }

    private fun getValues(): Collection<Job> {
        return mutableListOf(
            itemUseCases.getBrands()
                .onEach { brands ->
                    _state.value = state.value.copy(
                        brands = brands
                    )
                }
                .launchIn(viewModelScope),

            itemUseCases.getCategories()
                .onEach { categories ->
                    _state.value = state.value.copy(
                        categories = categories
                    )
                }
                .launchIn(viewModelScope),

            itemUseCases.getUnits()
                .onEach { units ->
                    _state.value = state.value.copy(
                        units = units
                    )
                }
                .launchIn(viewModelScope)
        )
    }



//    private suspend fun <T> Flow<List<T>>.flattenToList() =
//            flatMapConcat { it.asFlow() }.toList()
}