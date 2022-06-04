package com.bapp.pointofsalesapp.feature_item.presentation.add_edit_item

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.bapp.pointofsalesapp.feature_item.domain.model.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit

data class AddEditItemDetailsState(
    val brands: List<Brand> = emptyList(),
    val categories: List<Category> = emptyList(),
    val units: List<Unit> = emptyList(),
    val subUnitsOfCurrentUnit: List<SubUnit> ?= emptyList(),
    val pricesOfItem: List<Price> = emptyList(),
    val item: Item ?= null,
    val headerText: String = "Add Item",
    val itemName: String = "",
    val itemBrand: String = "",
    val itemCategory: String = "",
    val itemUnit: String = "",
    val itemUnitAbbreviation: String = "",
    val itemCost: String = ""
)
