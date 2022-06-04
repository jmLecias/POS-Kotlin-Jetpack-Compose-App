package com.bapp.pointofsalesapp.feature_item.data.data_source

import androidx.room.*
import com.bapp.pointofsalesapp.feature_item.domain.model.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.ItemWithPrices
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.UnitWithSubUnits
import kotlinx.coroutines.flow.Flow

@Dao
interface PriceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrice(price: Price)

    @Delete
    suspend fun deletePrice(price: Price)

    @Query("SELECT * FROM price")
    fun getPrices() :Flow<List<Price>>

    @Transaction
    @Query("SELECT * FROM price WHERE itemName = :itemName")
    fun getPricesOfItem(itemName: String): Flow<List<Price>>
}