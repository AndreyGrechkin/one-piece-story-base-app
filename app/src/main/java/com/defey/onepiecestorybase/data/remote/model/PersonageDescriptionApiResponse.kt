package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PersonageDescriptionEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonageDescriptionApiResponse(
    val response: List<PersonageDescriptionResponse>
)

@JsonClass(generateAdapter = true)
data class PersonageDescriptionResponse(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val description: String?,
    val image: String?,
    val personageType: String?,
    val surname: String?,
    val fruitId: Int?,
    val career: String?,
)

fun PersonageDescriptionResponse.toEntity() = PersonageDescriptionEntity(
    id = id,
    personageId = personageId,
    mangaId = mangaId,
    description = description,
    image = image,
    personageType = personageType,
    surname = surname,
    fruitId = fruitId,
    career = career,
    isNewPersonage = true
)