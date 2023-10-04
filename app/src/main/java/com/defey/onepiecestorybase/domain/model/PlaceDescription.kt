package com.defey.onepiecestorybase.domain.model

data class PlaceDescription(
    val id: Int,
    val placeId: Int,
    val mangaId: Int,
    val description: String?,
    val event: String?,
    val image: String?,
)
