package com.defey.onepiecestorybase.domain.model

data class PlaceItemTransit(
    val id: Int,
    val mangaId: Int,
    val placeId: Int,
    val personageId: Int,
    val outItemId: Int,
    val inItemId: Int
)
