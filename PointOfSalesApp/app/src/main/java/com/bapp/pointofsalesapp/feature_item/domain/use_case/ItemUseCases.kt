package com.bapp.pointofsalesapp.feature_item.domain.use_case

data class ItemUseCases(
    val getItems: GetItems,
    val getItemsOfBrand: GetItemsOfBrand,
    val getItemsOfCategory: GetItemsOfCategory,
    val getPricesOfItem: GetPricesOfItem,
    val getBrands: GetBrands,
    val getCategories: GetCategories,
    val getUnits: GetUnits,
    val getSubUnitsOfUnit: GetSubUnitsOfUnit,
    val getItemById: GetItemById,
    val getUnitByName: GetUnitByName,
    val deleteItem: DeleteItem,
    val addItem: AddItem,
    val addBrand: AddBrand,
    val addCategory: AddCategory,
    val addBrandCategoryCrossRef: AddBrandCategoryCrossRef,
    val addUnit: AddUnit,
    val addSubUnit: AddSubUnit,
    val addPrice: AddPrice,
)
