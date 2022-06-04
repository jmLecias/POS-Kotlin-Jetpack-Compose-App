package com.bapp.pointofsalesapp.feature_item.data.data_source

import androidx.room.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrand(brand: Brand)

    @Delete
    suspend fun deleteBrand(brand: Brand)

    @Update
    suspend fun updateBrand(brand: Brand)

    @Query("SELECT * FROM brand WHERE brandName = :brandName")
    suspend fun getBrandByName(brandName: String): Brand?

    @Query("SELECT * FROM brand")
    fun getBrands(): Flow<List<Brand>>
}