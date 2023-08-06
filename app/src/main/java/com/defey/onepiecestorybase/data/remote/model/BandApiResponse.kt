package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.BandEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BandApiResponse(
    val response: List<BandResponse>
)

@JsonClass(generateAdapter = true)
data class BandResponse(
    val id: Int,
    val mangaId: Int,
    val nameBand: String,
    val image: String?,
    val bandType: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
)

fun BandResponse.toEntity() = BandEntity(
    id = id,
    mangaId = mangaId,
    nameBand = nameBand,
    image = image,
    bandType = bandType,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp
)

