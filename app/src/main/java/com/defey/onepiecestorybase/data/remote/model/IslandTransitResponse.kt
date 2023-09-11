package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.IslandTransitEntity

data class IslandTransitResponse(
    val response: List<IslandTransitResponseBody>
)

data class IslandTransitResponseBody(
    val id: Int,
    val avatarId: Int,
    val placeId: Int,
    val posX: Double,
    val posY: Double
)

fun IslandTransitResponseBody.toEntity() = IslandTransitEntity(
    id = id,
    avatarId = avatarId,
    placeId = placeId,
    posX = posX,
    posY = posY
)
