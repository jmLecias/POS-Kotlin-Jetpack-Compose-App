package com.bapp.pointofsalesapp.feature_item.data.data_source

import androidx.room.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.model.Category
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface UnitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Delete
    suspend fun deleteUnit(unit: Unit)

    @Update
    suspend fun updateUnit(unit: Unit)

    @Query("SELECT * FROM unit")
    fun getUnits(): Flow<List<Unit>>

    @Query("SELECT * FROM unit WHERE unitName = :unitName")
    suspend fun getUnitByName(unitName: String): Unit?
}