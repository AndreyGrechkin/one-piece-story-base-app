package com.defey.onepiecestorybase.di

import android.content.Context
import androidx.room.Room
import com.defey.onepiecestorybase.data.local.database.OnePieceDataBase
import com.defey.onepiecestorybase.data.local.database.dao.BandDao
import com.defey.onepiecestorybase.data.local.database.dao.BandDescriptionDao
import com.defey.onepiecestorybase.data.local.database.dao.BandPersonageDao
import com.defey.onepiecestorybase.data.local.database.dao.BondDao
import com.defey.onepiecestorybase.data.local.database.dao.FruitDao
import com.defey.onepiecestorybase.data.local.database.dao.InventoryDao
import com.defey.onepiecestorybase.data.local.database.dao.MangaDao
import com.defey.onepiecestorybase.data.local.database.dao.PersonageDao
import com.defey.onepiecestorybase.data.local.database.dao.PersonageDescriptionDao
import com.defey.onepiecestorybase.data.local.database.dao.PersonageRewardDao
import com.defey.onepiecestorybase.data.local.database.dao.PersonageSkillDao
import com.defey.onepiecestorybase.data.local.database.dao.PersonageWeaponsDao
import com.defey.onepiecestorybase.data.local.database.dao.PlaceDao
import com.defey.onepiecestorybase.data.local.database.dao.PlaceDescriptionDao
import com.defey.onepiecestorybase.data.local.database.dao.PlaceItemDao
import com.defey.onepiecestorybase.data.local.database.dao.PlaceTransitItemDao
import com.defey.onepiecestorybase.data.local.database.dao.ShipDao
import com.defey.onepiecestorybase.presentation.utils.Constants.ONE_PIECE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        OnePieceDataBase::class.java,
        ONE_PIECE_DATABASE
    ).build()

    @Provides
    fun provideBandDao(dataBase: OnePieceDataBase): BandDao = dataBase.bandDao()

    @Provides
    fun provideBandDescriptionDao(dataBase: OnePieceDataBase): BandDescriptionDao = dataBase.bandDescriptionDao()

    @Provides
    fun provideBandPersonageDao(dataBase: OnePieceDataBase): BandPersonageDao = dataBase.bandPersonageDao()

    @Provides
    fun provideBondDao(dataBase: OnePieceDataBase): BondDao = dataBase.bondDao()

    @Provides
    fun provideFruitDao(dataBase: OnePieceDataBase): FruitDao = dataBase.fruitDao()

    @Provides
    fun provideInventoryDao(dataBase: OnePieceDataBase): InventoryDao = dataBase.inventoryDao()

    @Provides
    fun provideMangaDao(dataBase: OnePieceDataBase): MangaDao = dataBase.mangaDao()

    @Provides
    fun providePersonageDao(dataBase: OnePieceDataBase): PersonageDao = dataBase.personageDao()

    @Provides
    fun providePersonageDescriptionDao(dataBase: OnePieceDataBase): PersonageDescriptionDao = dataBase.personageDescriptionDao()

    @Provides
    fun providePersonageRewardDao(dataBase: OnePieceDataBase): PersonageRewardDao = dataBase.personageRewardDao()

    @Provides
    fun providePersonageSkillDao(dataBase: OnePieceDataBase): PersonageSkillDao = dataBase.personageSkillDao()

    @Provides
    fun providePersonageWeaponsDao(dataBase: OnePieceDataBase): PersonageWeaponsDao = dataBase.personageWeaponsDao()

    @Provides
    fun providePlaceDao(dataBase: OnePieceDataBase): PlaceDao = dataBase.placeDao()

    @Provides
    fun providePlaceDescriptionDao(dataBase: OnePieceDataBase): PlaceDescriptionDao = dataBase.placeDescriptionDao()

    @Provides
    fun providePlaceItemDao(dataBase: OnePieceDataBase): PlaceItemDao = dataBase.placeItemDao()

    @Provides
    fun providePlaceTransitItemDao(dataBase: OnePieceDataBase): PlaceTransitItemDao = dataBase.placeTransitItemDao()

    @Provides
    fun provideShipDao(dataBase: OnePieceDataBase): ShipDao = dataBase.shipDao()


}