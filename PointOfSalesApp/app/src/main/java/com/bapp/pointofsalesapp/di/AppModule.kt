package com.bapp.pointofsalesapp.di

import android.app.Application
import androidx.room.Room
import com.bapp.pointofsalesapp.feature_item.data.data_source.ItemDatabase
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import com.bapp.pointofsalesapp.feature_item.data.repository.AppRepositoryImpl
import com.bapp.pointofsalesapp.feature_item.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemDatabase(app: Application): ItemDatabase {
        return Room.databaseBuilder(
            app,
            ItemDatabase::class.java,
            ItemDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideAppRepository(db: ItemDatabase): AppRepository {
        return AppRepositoryImpl(
            db.itemDao,
            db.brandDao,
            db.categoryDao,
            db.brandCategoryCrossRefDao,
            db.priceDao,
            db.unitDao,
            db.subUnitDao
        )
    }

    @Provides
    @Singleton
    fun provideItemUseCases(repository: AppRepository): ItemUseCases {
        return ItemUseCases(
            getItems = GetItems(repository),
            getItemsOfBrand = GetItemsOfBrand(repository),
            getItemsOfCategory = GetItemsOfCategory(repository),
            getPricesOfItem = GetPricesOfItem(repository),
            getBrands = GetBrands(repository),
            getCategories = GetCategories(repository),
            getUnits = GetUnits(repository),
            getSubUnitsOfUnit = GetSubUnitsOfUnit(repository),
            getItemById = GetItemById(repository),
            getUnitByName = GetUnitByName(repository),
            deleteItem = DeleteItem(repository),
            addItem = AddItem(repository),
            addBrand = AddBrand(repository),
            addCategory = AddCategory(repository),
            addBrandCategoryCrossRef = AddBrandCategoryCrossRef(repository),
            addUnit = AddUnit(repository),
            addSubUnit = AddSubUnit(repository),
            addPrice = AddPrice(repository)
        )
    }
}