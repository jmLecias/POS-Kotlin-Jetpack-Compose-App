package com.bapp.pointofsalesapp.feature_item.data.data_source

import androidx.room.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Item
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.UnitWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item WHERE itemID = :itemID")
    suspend fun getItemByID(itemID: Int): Item?

    @Query("SELECT * FROM item")
    fun getItems(): Flow<List<Item>>

    @Transaction
    @Query("SELECT * FROM item WHERE brandName = :brandName")
    fun getItemsOfBrand(brandName: String): Flow<List<Item>>

    @Transaction
    @Query("SELECT * FROM item WHERE categoryName = :categoryName")
    fun getItemsOfCategory(categoryName: String): Flow<List<Item>>

//    @Transaction
//    @Query("SELECT * FROM item WHERE unitName = :unitName")
//    fun getItemsOfUnit(unitName: String): Flow<List<UnitWithItems>>
}