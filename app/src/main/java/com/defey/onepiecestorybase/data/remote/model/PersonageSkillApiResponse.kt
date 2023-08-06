package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PersonageSkillEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonageSkillApiResponse(
    val response: List<PersonageSkillResponse>
)

@JsonClass(generateAdapter = true)
data class PersonageSkillResponse(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val nameSkill: String,
    val description: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
)

fun PersonageSkillResponse.toEntity() = PersonageSkillEntity(
    id = id,
    personageId = personageId,
    mangaId = mangaId,
    nameSkill = nameSkill,
    description = description,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp
)
