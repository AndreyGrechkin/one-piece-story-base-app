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
import com.defey.onepiecestorybase.data.local.localDataSource.PlaceLocalDataSource
import com.defey.onepiecestorybase.data.local.model.BandDescriptionEntity
import com.defey.onepiecestorybase.data.local.model.BandEntity
import com.defey.onepiecestorybase.data.local.model.BandPersonageEntity
import com.defey.onepiecestorybase.data.local.model.BondEntity
import com.defey.onepiecestorybase.data.local.model.FruitEntity
import com.defey.onepiecestorybase.data.local.model.InventoryEntity
import com.defey.onepiecestorybase.data.local.model.MangaEntity
import com.defey.onepiecestorybase.data.local.model.PersonageDescriptionEntity
import com.defey.onepiecestorybase.data.local.model.PersonageEntity
import com.defey.onepiecestorybase.data.local.model.PersonageRewardEntity
import com.defey.onepiecestorybase.data.local.model.PersonageSkillEntity
import com.defey.onepiecestorybase.data.local.model.PersonageWeaponsEntity
import com.defey.onepiecestorybase.data.local.model.PlaceDescriptionEntity
import com.defey.onepiecestorybase.data.local.model.PlaceEntity
import com.defey.onepiecestorybase.data.local.model.PlaceItemEntity
import com.defey.onepiecestorybase.data.local.model.PlaceTransitItemEntity
import com.defey.onepiecestorybase.data.local.model.ShipEntity
import com.defey.onepiecestorybase.data.local.model.asDomain
import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.MapResponse
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.domain.model.Place
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
    private val shipDao: ShipDao,
    private val local: PlaceLocalDataSource
) : PlaceRepository {
    override suspend fun getMapById(id: Int): MapResponse {
        return api.getMapById(id)
    }

    override suspend fun syncMapById(id: Int): Response<Unit> {
        return safeApiCall {
            val mapAll = api.getMapById(id)
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
            addBandDao(band)
            addBandDescriptionDao(bandDesc)
            addBandPersonageDao(bandPerson)
            addBondDao(bond)
            addFruitDao(fruit)
            addSubjectDao(inventory)
            addMangaDao(manga)
            addPersonageDao(personage)
            addPersonageDescriptionDao(personDesc)
            addRewardDao(reward)
            addSkillDao(skill)
            addWeaponDao(weapon)
            addPlaceDao(place)
            addPlaceDescriptionDao(placeDesc)
            addPlaceItemDao(placeItem)
            addPlaceTransitItemDao(placeTransit)
            addShipDao(ship)
        }
    }

    private suspend fun addShipDao(ship: List<ShipEntity>) {
        if (ship.isNotEmpty()) {
            shipDao.addShip(ship)
        }
    }

    private suspend fun addPlaceTransitItemDao(placeTransit: List<PlaceTransitItemEntity>) {
        if (placeTransit.isNotEmpty()) {
            placeTransitItemDao.addPlaceTransit(placeTransit)
        }
    }

    private suspend fun addPlaceItemDao(placeItem: List<PlaceItemEntity>) {
        if (placeItem.isNotEmpty()) {
            placeItemDao.addPlaceItem(placeItem)
        }
    }

    private suspend fun addPlaceDescriptionDao(placeDescription: List<PlaceDescriptionEntity>) {
        if (placeDescription.isNotEmpty()) {
            placeDescriptionDao.addPlaceDescription(placeDescription)
        }
    }

    private suspend fun addPlaceDao(place: PlaceEntity) {
        placeDao.addPlace(place)
    }

    private suspend fun addWeaponDao(weapons: List<PersonageWeaponsEntity>) {
        if (weapons.isNotEmpty()) {
            weaponsDao.addPersonageWeapon(weapons)
            personageDescriptionDao.updateNewPersonage(weapons.map { it.personageId }.distinct())
        }
    }

    private suspend fun addSkillDao(skill: List<PersonageSkillEntity>) {
        if (skill.isNotEmpty()) {
            skillDao.addPersonageSkill(skill)
            personageDescriptionDao.updateNewPersonage(skill.map { it.personageId }.distinct())
        }
    }

    private suspend fun addRewardDao(reward: List<PersonageRewardEntity>) {
        if (reward.isNotEmpty()) {
            rewardDao.addPersonageReward(reward)
            personageDescriptionDao.updateNewPersonage(reward.map { it.personageId }.distinct())
        }
    }

    private suspend fun addPersonageDescriptionDao(personageDescription: List<PersonageDescriptionEntity>) {
        if (personageDescription.isNotEmpty()) {
            personageDescriptionDao.addPersonageDescription(personageDescription)
        }
    }

    private suspend fun addPersonageDao(personage: List<PersonageEntity>) {
        if (personage.isNotEmpty()) {
            personageDao.addPersonage(personage)
        }
    }

    private suspend fun addMangaDao(manga: List<MangaEntity>) {
        if (manga.isNotEmpty()) {
            mangaDao.addManga(manga)
        }
    }

    private suspend fun addBandDao(band: List<BandEntity>) {
        if (band.isNotEmpty()) {
            bandDao.addBand(band)
        }
    }

    private suspend fun addBandDescriptionDao(bandDescription: List<BandDescriptionEntity>) {
        if (bandDescription.isNotEmpty()) {
            bandDescriptionDao.addBandDescription(bandDescription)
        }
    }

    private suspend fun addBandPersonageDao(bandPersonage: List<BandPersonageEntity>) {
        if (bandPersonage.isNotEmpty()) {
            bandPersonageDao.addBandPersonage(bandPersonage)
            personageDescriptionDao.updateNewPersonage(bandPersonage.map { it.personageId }
                .distinct())
        }
    }

    private suspend fun addBondDao(bond: List<BondEntity>) {
        if (bond.isNotEmpty()) {
            bondDao.addBond(bond)
        }
    }

    private suspend fun addFruitDao(fruit: List<FruitEntity>) {
        if (fruit.isNotEmpty()) {
            fruitDao.addFruit(fruit)
        }
    }

    private suspend fun addSubjectDao(subject: List<InventoryEntity>) {
        if (subject.isNotEmpty()) {
            inventoryDao.addInventory(subject)
        }
    }

    override fun getLastPlace(): Flow<Place?> {
        return placeDao.getLastPlace().map { it?.asDomain() }
    }

    override fun getAllPlaceFlow(): Flow<List<Place>> {
        return placeDao.getAllPlaceFlow().map { value -> value.map { it.asDomain() } }
    }

    override fun getLocation(id: Int): Flow<Place> {
        return local.getLocation(id).map { it.asDomain() }
    }
}