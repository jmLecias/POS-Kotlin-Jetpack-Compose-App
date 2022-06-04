package com.bapp.pointofsalesapp.feature_item.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bapp.pointofsalesapp.feature_item.domain.model.*
import com.bapp.pointofsalesapp.feature_item.domain.model.Unit
import com.bapp.pointofsalesapp.feature_item.domain.model.relations.BrandCategoryCrossRef

@Database(
    entities = [
        Item::class,
        Brand::class,
        Category::class,
        BrandCategoryCrossRef::class,
        Price::class,
        Unit::class,
        SubUnit::class
               ],
    version = 1
)
abstract class ItemDatabase: RoomDatabase() {

    abstract val itemDao: ItemDao
    abstract val brandDao: BrandDao
    abstract val categoryDao: CategoryDao
    abstract val brandCategoryCrossRefDao: BrandCategoryCrossRefDao
    abstract val priceDao: PriceDao
    abstract val unitDao: UnitDao
    abstract val subUnitDao: SubUnitDao

    companion object {
        const val DATABASE_NAME = "item_db"
    }

//    companion object {
//        @Volatile
//        private var INSTANCE: ItemDatabase? = null
//
//        fun getInstance(context: Context): ItemDatabase {
//            synchronized(this) {
//                return INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    ItemDatabase::class.java,
//                    "local_db"
//                ).build().also {
//                    INSTANCE = it
//                }
//            }
//        }
//    }
}