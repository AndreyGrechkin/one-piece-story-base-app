package com.defey.onepiecestorybase.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.defey.onepiecestorybase.data.local.database.dao.BandDao
import com.defey.onepiecestorybase.data.local.database.dao.BandDescriptionDao
import com.defey.onepiecestorybase.data.local.database.dao.BandPersonageDao
import com.defey.onepiecestorybase.data.local.database.dao.BondDao
import com.defey.onepiecestorybase.data.local.database.dao.FruitDao
import com.defey.onepiecestorybase.data.local.database.dao.InventoryDao
import com.defey.onepiecestorybase.data.local.database.dao.IslandDao
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
import com.defey.onepiecestorybase.data.local.model.BandDescriptionEntity
import com.defey.onepiecestorybase.data.local.model.BandEntity
import com.defey.onepiecestorybase.data.local.model.BandPersonageEntity
import com.defey.onepiecestorybase.data.local.model.BondEntity
import com.defey.onepiecestorybase.data.local.model.FruitEntity
import com.defey.onepiecestorybase.data.local.model.InventoryEntity
import com.defey.onepiecestorybase.data.local.model.IslandEntity
import com.defey.onepiecestorybase.data.local.model.IslandTransitEntity
import com.defey.onepiecestorybase.data.local.model.MangaEntity
import com.defey.onepiecestorybase.data.local.model.PersonageDescriptionEntity
import com.defey.onepiecestorybase.data.local.model.PersonageEntity
import com.defey.onepiecestorybase.data.local.model.PersonageIslandEntity
import com.defey.onepiecestorybase.data.local.model.PersonageRewardEntity
import com.defey.onepiecestorybase.data.local.model.PersonageSkillEntity
import com.defey.onepiecestorybase.data.local.model.PersonageWeaponsEntity
import com.defey.onepiecestorybase.data.local.model.PlaceDescriptionEntity
import com.defey.onepiecestorybase.data.local.model.PlaceEntity
import com.defey.onepiecestorybase.data.local.model.PlaceItemEntity
import com.defey.onepiecestorybase.data.local.model.PlaceTransitItemEntity
import com.defey.onepiecestorybase.data.local.model.ShipEntity

@Database(
    entities = [
        BandEntity::class,
        BandDescriptionEntity::class,
        BandPersonageEntity::class,
        BondEntity::class,
        FruitEntity::class,
        InventoryEntity::class,
        MangaEntity::class,
        PersonageEntity::class,
        PersonageDescriptionEntity::class,
        PersonageRewardEntity::class,
        PersonageSkillEntity::class,
        PersonageWeaponsEntity::class,
        PlaceEntity::class,
        PlaceDescriptionEntity::class,
        PlaceItemEntity::class,
        PlaceTransitItemEntity::class,
        ShipEntity::class,
        IslandEntity::class,
        PersonageIslandEntity::class,
        IslandTransitEntity::class
    ], version = 1, exportSchema = true
)
abstract class OnePieceDataBase : RoomDatabase() {

    abstract fun bandDao(): BandDao
    abstract fun bandDescriptionDao(): BandDescriptionDao
    abstract fun bandPersonageDao(): BandPersonageDao
    abstract fun bondDao(): BondDao
    abstract fun fruitDao(): FruitDao
    abstract fun inventoryDao(): InventoryDao
    abstract fun mangaDao(): MangaDao
    abstract fun personageDao(): PersonageDao
    abstract fun personageDescriptionDao(): PersonageDescriptionDao
    abstract fun personageRewardDao(): PersonageRewardDao
    abstract fun personageSkillDao(): PersonageSkillDao
    abstract fun personageWeaponsDao(): PersonageWeaponsDao
    abstract fun placeDao(): PlaceDao
    abstract fun placeDescriptionDao(): PlaceDescriptionDao
    abstract fun placeItemDao(): PlaceItemDao
    abstract fun placeTransitItemDao(): PlaceTransitItemDao
    abstract fun shipDao(): ShipDao
    abstract fun islandDao(): IslandDao
}