package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.BandDescriptionEntity

data class BandDescriptionApiResponse(
    val response: List<BandDescriptionResponse>
)

data class BandDescriptionResponse(
    val id: Int,
    val bandId: Int,
    val mangaId: Int,
    val description: String?,
)

fun BandDescriptionResponse.toEntity() = BandDescriptionEntity(
    id = id,
    bandId = bandId,
    mangaId = mangaId,
    description = description,
    isNewBand = true
)
