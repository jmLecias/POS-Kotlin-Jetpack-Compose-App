package com.bapp.pointofsalesapp.feature_item.domain.repository

import com.bapp.pointofsalesapp.feature_item.domain.model.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.*
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    // insert
    suspend fun insertItem(item: Item)

    suspend fun insertBrand(brand: Brand)

    suspend fun insertCategory(category: Category)

    suspend fun insertBrandCategoryCrossRef(brandCategoryCrossRef: BrandCategoryCrossRef)

    suspend fun insertPrice(price: Price)

    suspend fun insertUnit(unit: Unit)

    suspend fun insertSubUnit(subUnit: SubUnit)

    // delete
    suspend fun deleteItem(item: Item)

    suspend fun deleteBrand(brand: Brand)

    suspend fun deleteCategory(category: Category)

    suspend fun deleteBrandCategoryCrossRef(brandCategoryCrossRef: BrandCategoryCrossRef)

    suspend fun deletePrice(price: Price)

    suspend fun deleteUnit(unit: Unit)

    suspend fun deleteSubUnit(subUnit: SubUnit)


    // update
    suspend fun updateBrand(brand: Brand)

    suspend fun updateCategory(category: Category)

    suspend fun updateUnit(unit: Unit)


    // get entity by name or ID
    suspend fun getItemByID(itemID: Int): Item?

    suspend fun getBrandByName(brandName: String): Brand?

    suspend fun getCategoryByName(categoryName: String): Category?

    suspend fun getUnitByName(unitName: String): Unit?


    // get all specific entities
    fun getItems(): Flow<List<Item>>

    fun getBrands(): Flow<List<Brand>>

    fun getCategories(): Flow<List<Category>>

    fun getPrices() :Flow<List<Price>>

    fun getUnits(): Flow<List<Unit>>


    // item features
    fun getItemsOfBrand(brandName: String): Flow<List<Item>>

    fun getItemsOfCategory(categoryName: String): Flow<List<Item>>

//    fun getItemsOfUnit(unitName: String): Flow<List<UnitWithItems>>


    // brand and category features
    suspend fun getCategoriesOfBrand(brandName: String): List<BrandWithCategories>

    suspend fun getBrandsOfCategory(categoryName: String): List<CategoryWithBrands>


    // price features
    fun getPricesOfItem(itemName: String): Flow<List<Price>>


    // subUnit features
    fun getSubUnitsOfUnit(unitName: String): Flow<List<SubUnit>>

}
