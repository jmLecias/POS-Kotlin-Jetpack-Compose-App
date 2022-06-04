package com.bapp.pointofsalesapp.feature_item.data.data_source

import androidx.room.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Category
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.BrandCategoryCrossRef
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.BrandWithCategories
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.CategoryWithBrands

@Dao
interface BrandCategoryCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrandCategoryCrossRef(brandCategoryCrossRef: BrandCategoryCrossRef)

    @Delete
    suspend fun deleteBrandCategoryCrossRef(brandCategoryCrossRef: BrandCategoryCrossRef)

    @Update
    suspend fun updateBrandCategoryCrossRef(brandCategoryCrossRef: BrandCategoryCrossRef)

    @Transaction
    @Query("SELECT * FROM brand WHERE brandName = :brandName")
    suspend fun getCategoriesOfBrand(brandName: String): List<BrandWithCategories>

    @Transaction
    @Query("SELECT * FROM category WHERE categoryName = :categoryName")
    suspend fun getBrandsOfCategory(categoryName: String): List<CategoryWithBrands>
}