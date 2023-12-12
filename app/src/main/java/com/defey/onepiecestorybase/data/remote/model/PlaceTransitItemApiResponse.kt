package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PlaceTransitItemEntity

data class PlaceTransitItemApiResponse(
    val response: List<PlaceTransitItemResponse>
)

data class PlaceTransitItemResponse(
    val id: Int,
    val mangaId: Int,
    val placeId: Int,
    val personageId: Int,
    val outItemId: Int,
    val inItemId: Int
)

fun PlaceTransitItemResponse.toEntity() = PlaceTransitItemEntity(
    id = id,
    mangaId = mangaId,
    placeId = placeId,
    personageId = personageId,
    outItemId = outItemId,
    inItemId = inItemId
)