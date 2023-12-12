package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.BandEntity

data class BandApiResponse(
    val response: List<BandResponse>
)

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

