package com.bapp.pointofsalesapp.feature_item.data.data_source

import androidx.room.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.model.Category
import com.bapp.pointofsalesapp.feature_item.domain.model.SubUnit
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.UnitWithSubUnits
import kotlinx.coroutines.flow.Flow

@Dao
interface SubUnitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubUnit(subUnit: SubUnit)

    @Delete
    suspend fun deleteSubUnit(subUnit: SubUnit)

    @Transaction
    @Query("SELECT * FROM subunit WHERE unitName = :unitName")
    fun getSubUnitsOfUnit(unitName: String): Flow<List<SubUnit>>
}