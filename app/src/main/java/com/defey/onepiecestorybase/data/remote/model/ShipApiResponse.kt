package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.ShipEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShipApiResponse(
    val response: List<ShipResponse>
)

@JsonClass(generateAdapter = true)
data class ShipResponse(
    val id: Int,
    val bandId: Int,
    val mangaId: Int,
    val nameShip: String?,
    val description: String?,
    val oldShip: Int?,
    val nameJp: String?,
    val transcriptionJp: String?,
    val image: String?,
)

fun ShipResponse.toEntity() = ShipEntity(
    id = id,
    bandId = bandId,
    mangaId = mangaId,
    nameShip = nameShip,
    description = description,
    oldShip = oldShip,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp,
    image = image
)