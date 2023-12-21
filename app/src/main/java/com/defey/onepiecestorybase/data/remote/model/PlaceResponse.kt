package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PlaceEntity

data class PlaceResponse(
    val id: Int,
    val namePlace: String?,
    val country: String?,
    val mangaId: Int,
    val sea: String?,
    val islandType: String?,
    val nameIsland: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
    val timeStep: Long,
    val placeDetailImage: String?,
)

fun PlaceResponse.toEntity() = PlaceEntity(
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
