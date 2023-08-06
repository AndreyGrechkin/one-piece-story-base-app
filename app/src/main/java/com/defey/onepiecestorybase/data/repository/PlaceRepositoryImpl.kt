package com.defey.onepiecestorybase.data.repository

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
import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.MapResponse
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val api: OnePieceApi,
    private val bandDao: BandDao,
    private val bandDescriptionDao: BandDescriptionDao,
    private val bandPersonageDao: BandPersonageDao,
    private val bondDao: BondDao,
    private val fruitDao: FruitDao,
    private val inventoryDao: InventoryDao,
    private val mangaDao: MangaDao,
    private val personageDao: PersonageDao,
    private val personageDescriptionDao: PersonageDescriptionDao,
    private val rewardDao: PersonageRewardDao,
    private val skillDao: PersonageSkillDao,
    private val weaponsDao: PersonageWeaponsDao,
    private val placeDao: PlaceDao,
    private val placeDescriptionDao: PlaceDescriptionDao,
    private val placeItemDao: PlaceItemDao,
    private val placeTransitItemDao: PlaceTransitItemDao,
    private val shipDao: ShipDao
) : PlaceRepository {
    override suspend fun getMapById(id: String): MapResponse {
        return api.getMapById(id)
    }

    override suspend fun syncMapById(id: Int) {
        mangaDao.getManga()
        val mapAll = api.getMapById(id.toString())
        val band = mapAll.band.map { it.toEntity() }
        val bandDesc = mapAll.bandDescription.map { it.toEntity() }
        val bandPerson = mapAll.bandPersonage.map { it.toEntity() }
        val bond = mapAll.bond.map { it.toEntity() }
        val fruit = mapAll.fruit.map { it.toEntity() }
        val inventory = mapAll.inventory.map { it.toEntity() }
        val manga = mapAll.manga.map { it.toEntity() }
        val personage = mapAll.personages.map { it.toEntity() }
        val personDesc = mapAll.personageDescription.map { it.toEntity() }
        val reward = mapAll.personageReward.map { it.toEntity() }
        val skill = mapAll.personageSkill.map { it.toEntity() }
        val weapon = mapAll.personageWeapon.map { it.toEntity() }
        val place = mapAll.toEntity()
        val placeDesc = mapAll.placeDescription.map { it.toEntity() }
        val placeItem = mapAll.placeItem.map { it.toEntity() }
        val placeTransit = mapAll.placeTransit.map { it.toEntity() }
        val ship = mapAll.ship.map { it.toEntity() }
        bandDao.addBand(band)
        bandDescriptionDao.addBandDescription(bandDesc)
        bandPersonageDao.addBandPersonage(bandPerson)
        bondDao.addBond(bond)
        fruitDao.addFruit(fruit)
        inventoryDao.addInventory(inventory)
        mangaDao.addManga(manga)
        personageDao.addPersonage(personage)
        personageDescriptionDao.addPersonageDescription(personDesc)
        rewardDao.addPersonageReward(reward)
        skillDao.addPersonageSkill(skill)
        weaponsDao.addPersonageWeapon(weapon)
        placeDao.addPlace(place)
        placeDescriptionDao.addPlaceDescription(placeDesc)
        placeItemDao.addPlaceItem(placeItem)
        placeTransitItemDao.addPlaceTransit(placeTransit)
        shipDao.addShip(ship)
    }


}