package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.MangaEntity

data class MangaApiResponse(
    val manges: List<MangaResponse>
)

data class MangaResponse(
    val id: Int,
    val volume: String?,
    val chapter: String?,
    val animeType: String?,
    val animeSeries: String?,
    val description: String?,
    val placeId: Int
)

fun MangaResponse.toEntity() = MangaEntity(
    id = id,
    volume = volume,
    chapter = chapter,
    animeType = animeType,
    animeSeries = animeSeries,
    description = description,
    placeId = placeId
)
