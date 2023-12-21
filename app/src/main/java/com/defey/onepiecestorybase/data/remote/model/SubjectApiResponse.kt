package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.SubjectEntity

data class SubjectApiResponse(
    val response: List<SubjectResponse>
)

data class SubjectResponse(
    val id: Int,
    val mangaId: Int,
    val placeId: Int,
    val image: String?,
    val description: String?,
    val name: String,
    val nameJp: String?,
    val transcriptionJp: String?,
)

fun SubjectResponse.toEntity() = SubjectEntity(
    id = id,
    mangaId = mangaId,
    placeId = placeId,
    image = image,
    description = description,
    name = name,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp,
    isNewSubject = true
)