package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PlaceEntity

data class MapResponse(
    val id: Int,
    val namePlace: String?,
    val country: String?,
    val mangaId: Int,
    val manga: List<MangaResponse>,
    val sea: String?,
    val islandType: String?,
    val nameIsland: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
    val timeStep: Long,
    val placeDetailImage: String?,
    val band: List<BandResponse>,
    val bandDescription: List<BandDescriptionResponse>,
    val bandPersonage: List<BandPersonageResponse>,
    val bond: List<BondResponse>,
    val fruit: List<FruitResponse>,
    val inventory: List<InventoryResponse>,
    val personages: List<PersonageResponse>,
    val personageDescription: List<PersonageDescriptionResponse>,
    val personageReward: List<PersonageRewardResponse>,
    val personageSkill: List<PersonageSkillResponse>,
    val personageWeapon: List<PersonageWeaponsResponse>,
    val placeDescription: List<PlaceDescriptionResponse>,
    val placeItem: List<PlaceItemResponse>,
    val placeTransit: List<PlaceTransitItemResponse>,
    val ship: List<ShipResponse>
)

fun MapResponse.toEntity() = PlaceEntity(
    id = id,
    namePlace = namePlace,
    country = country,
    mangaId = mangaId,
    sea = sea,
    islandType = islandType,
    nameIsland = nameIsland,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp,
    timeStep = timeStep,
    placeDetailImage = placeDetailImage
)