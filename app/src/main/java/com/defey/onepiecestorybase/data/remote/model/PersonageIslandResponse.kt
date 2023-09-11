package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PersonageIslandEntity

data class PersonageIslandResponse(
    val response: List<PersonageIslandResponseBody>
)

data class PersonageIslandResponseBody(
    val id: Int,
    val placeId: Int,
    val nameAvatar: String,
    val nameImage: String,
    val posX: Double,
    val posY: Double,
)

fun PersonageIslandResponseBody.toEntity() = PersonageIslandEntity(
    id = id,
    placeId = placeId,
    nameAvatar = nameAvatar,
    nameImage = nameImage,
    posX = posX,
    posY = posY
)
