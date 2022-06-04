package com.bapp.pointofsalesapp.feature_item.data.repository

import com.bapp.pointofsalesapp.feature_item.data.data_source.*
import com.bapp.pointofsalesapp.feature_item.domain.model.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.*
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(
    private val itemDao: ItemDao,
    private val brandDao: BrandDao,
    private val categoryDao: CategoryDao,
    private val brandCategoryCrossRefDao: BrandCategoryCrossRefDao,
    private val priceDao: PriceDao,
    private val unitDao: UnitDao,
    private val subUnitDao: SubUnitDao
): AppRepository {

    // insert
    override suspend fun insertItem(item: Item) {
        itemDao.insertItem(item)
    }

    override suspend fun insertBrand(brand: Brand) {
        brandDao.insertBrand(brand)
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category)
    }

    override suspend fun insertBrandCategoryCrossRef(brandCategoryCrossRef: BrandCategoryCrossRef) {
        brandCategoryCrossRefDao.insertBrandCategoryCrossRef(brandCategoryCrossRef)
    }

    override suspend fun insertPrice(price: Price) {
        priceDao.insertPrice(price)
    }

    override suspend fun insertUnit(unit: Unit) {
        unitDao.insertUnit(unit)
    }

    override suspend fun insertSubUnit(subUnit: SubUnit) {
        subUnitDao.insertSubUnit(subUnit)
    }


    // delete
    override suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    override suspend fun deleteBrand(brand: Brand) {
        brandDao.deleteBrand(brand)
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    override suspend fun deleteBrandCategoryCrossRef(brandCategoryCrossRef: BrandCategoryCrossRef) {
        brandCategoryCrossRefDao.deleteBrandCategoryCrossRef(brandCategoryCrossRef)
    }

    override suspend fun deletePrice(price: Price) {
        priceDao.deletePrice(price)
    }

    override suspend fun deleteUnit(unit: Unit) {
        unitDao.deleteUnit(unit)
    }

    override suspend fun deleteSubUnit(subUnit: SubUnit) {
        subUnitDao.deleteSubUnit(subUnit)
    }


    // update
    override suspend fun updateBrand(brand: Brand) {
        brandDao.updateBrand(brand)
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category)
    }

    override suspend fun updateUnit(unit: Unit) {
        unitDao.updateUnit(unit)
    }


    // get entity by name
    override suspend fun getItemByID(itemID: Int): Item? {
        return itemDao.getItemByID(itemID)
    }

    override suspend fun getBrandByName(brandName: String): Brand? {
        return brandDao.getBrandByName(brandName)
    }

    override suspend fun getCategoryByName(categoryName: String): Category? {
        return categoryDao.getCategoryByName(categoryName)
    }

    override suspend fun getUnitByName(unitName: String): Unit? {
        return unitDao.getUnitByName(unitName)
    }


    // get all specific entities
    override fun getItems(): Flow<List<Item>> {
        return itemDao.getItems()
    }

    override fun getBrands(): Flow<List<Brand>> {
        return brandDao.getBrands()
    }

    override fun getCategories(): Flow<List<Category>> {
        return categoryDao.getCategories()
    }

    override fun getPrices(): Flow<List<Price>> {
        return priceDao.getPrices()
    }

    override fun getUnits(): Flow<List<Unit>> {
        return unitDao.getUnits()
    }


    // item features
    override fun getItemsOfBrand(brandName: String): Flow<List<Item>> {
        return itemDao.getItemsOfBrand(brandName)
    }

    override fun getItemsOfCategory(categoryName: String): Flow<List<Item>> {
        return itemDao.getItemsOfCategory(categoryName)
    }

//    override fun getItemsOfUnit(unitName: String): Flow<List<UnitWithItems>> {
//        return itemDao.getItemsOfUnit(unitName)
//    }


    // brand and category features
    override suspend fun getCategoriesOfBrand(brandName: String): List<BrandWithCategories> {
        return brandCategoryCrossRefDao.getCategoriesOfBrand(brandName)
    }

    override suspend fun getBrandsOfCategory(categoryName: String): List<CategoryWithBrands> {
        return brandCategoryCrossRefDao.getBrandsOfCategory(categoryName)
    }


    // price features
    override fun getPricesOfItem(itemName: String): Flow<List<Price>> {
        return priceDao.getPricesOfItem(itemName)
    }


    // subUnit features
    override fun getSubUnitsOfUnit(unitName: String): Flow<List<SubUnit>> {
        return  subUnitDao.getSubUnitsOfUnit(unitName)
    }


}