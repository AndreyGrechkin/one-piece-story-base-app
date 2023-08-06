package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.BandPersonageEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BandPersonageApiResponse(
    val response: List<BandPersonageResponse>
)

@JsonClass(generateAdapter = true)
data class BandPersonageResponse(
    val id: Int,
    val personageId: Int,
    val bandId: Int,
    val mangaId: Int,
    val career: String?,
    val oldPersonage: Boolean
)

fun BandPersonageResponse.toEntity() = BandPersonageEntity(
    id = id,
    personageId = personageId,
    bandId = bandId,
    mangaId = mangaId,
    career = career,
    oldPersonage = oldPersonage
)