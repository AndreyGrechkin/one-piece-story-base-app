package com.defey.onepiecestorybase.domain.model


data class Island(
    val id: Int,
    val name: String,
    val nameImage: String,
    val posX: Double,
    val posY: Double,
    val placeId: Int?
)

fun Island.toIslandPlace(isEnable: Boolean) = IslandPlace(
    id = name,
    placeId = placeId,
    name = name,
    nameImage = nameImage,
    x = posX,
    y = posY,
    isEnabled = isEnable
)