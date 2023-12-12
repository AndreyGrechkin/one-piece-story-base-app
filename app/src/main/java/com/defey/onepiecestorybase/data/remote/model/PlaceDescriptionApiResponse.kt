package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PlaceDescriptionEntity

data class PlaceDescriptionApiResponse(
    val response: List<PlaceDescriptionResponse>
)

data class PlaceDescriptionResponse(
    val id: Int,
    val placeId: Int,
    val mangaId: Int,
    val description: String?,
    val event: String?,
    val image: String?,
)

fun PlaceDescriptionResponse.toEntity() = PlaceDescriptionEntity(
    id = id,
    placeId = placeId,
    mangaId = mangaId,
    description = description,
    event = event,
    image = image,
    isNewLocation = true
)