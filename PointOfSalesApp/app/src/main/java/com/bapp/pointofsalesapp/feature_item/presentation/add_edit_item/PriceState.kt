package com.bapp.pointofsalesapp.feature_item.presentation.add_edit_item

import com.bapp.pointofsalesapp.feature_item.domain.model.Price
import com.bapp.pointofsalesapp.feature_item.domain.model.SubUnit

data class PriceState (
    val price: Price ?= null,
    val itemPrice: String = "",
    val priceRatio: String = "",
    val unitName: String = "",
    val unitAbbreviation: String = "",
    val isDropdownExpanded: Boolean = false
)