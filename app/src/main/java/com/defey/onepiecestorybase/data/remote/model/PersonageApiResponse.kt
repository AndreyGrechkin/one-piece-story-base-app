package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PersonageEntity

data class PersonageApiResponse(
    val response: List<PersonageResponse>
)

data class PersonageResponse(
    val id: Int,
    val mangaId: Int,
    val placeId: Int,
    val name: String,
    val nameJp: String?,
    val transcriptionJp: String?,
    val avatar: String?
)

fun PersonageResponse.toEntity() = PersonageEntity(
    id = id,
    mangaId = mangaId,
    placeId = placeId,
    name = name,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp,
    avatar = avatar
)