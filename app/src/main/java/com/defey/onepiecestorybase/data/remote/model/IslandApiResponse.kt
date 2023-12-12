package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.IslandEntity

data class IslandApiResponse(
    val response: List<IslandResponse>
)

data class IslandResponse(
    val id: Int,
    val name: String,
    val nameImage: String,
    val posX: Double,
    val posY: Double,
    val placeId: Int?
)

fun IslandResponse.toEntity() = IslandEntity(
    id = id,
    name = name,
    nameImage = nameImage,
    posX = posX,
    posY = posY,
    placeId = placeId
)
