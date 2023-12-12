package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PlaceItemEntity

data class PlaceItemApiResponse(
    val response: List<PlaceItemResponse>
)

data class PlaceItemResponse(
    val id: Int,
    val placeId: Int,
    val nameItem: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
    val description: String?,
    val image: String?,
    val posX: Double,
    val posY: Double,
)

fun PlaceItemResponse.toEntity() = PlaceItemEntity(
    id = id,
    placeId = placeId,
    nameItem = nameItem,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp,
    description = description,
    image = image,
    posX = posX,
    posY = posY
)