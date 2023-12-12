package com.defey.onepiecestorybase.domain.model

data class PlaceItem(
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
