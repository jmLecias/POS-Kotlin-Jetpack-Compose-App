package com.bapp.pointofsalesapp.feature_item.data.data_source

import androidx.room.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Query("SELECT * FROM category WHERE categoryName = :categoryName")
    suspend fun getCategoryByName(categoryName: String): Category?

    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<Category>>
}