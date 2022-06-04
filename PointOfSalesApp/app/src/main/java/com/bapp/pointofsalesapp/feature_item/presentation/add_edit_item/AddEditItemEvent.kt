package com.bapp.pointofsalesapp.feature_item.presentation.add_edit_item

import androidx.compose.runtime.MutableState
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit

sealed class AddEditItemEvent {
    data class OnItemNameChange(val itemName: String): AddEditItemEvent()
    data class OnBrandNameChange(val brandName: String): AddEditItemEvent()
    data class OnCategoryNameChange(val categoryName: String): AddEditItemEvent()
    data class OnItemCostChange(val itemCost: String): AddEditItemEvent()
    data class OnUnitNameChange(val unitName: String): AddEditItemEvent()
    data class OnUnitDropdownClick(val unit: Unit): AddEditItemEvent()
    data class OnUnitAbbreviationChange(val unitAbbreviation: String): AddEditItemEvent()
    object OnAddPriceClick: AddEditItemEvent()
    object OnUndoAddPriceClick: AddEditItemEvent()
    object OnSaveItemClick: AddEditItemEvent()

}
