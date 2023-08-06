package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.FruitEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FruitApiResponse(
    val response: List<FruitResponse>
)

@JsonClass(generateAdapter = true)
data class FruitResponse(
    val id: Int,
    val mangaId: Int,
    val nameFruit: String,
    val fruitType: String?,
    val image: String?,
    val description: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
)

fun FruitResponse.toEntity() = FruitEntity(
    id = id,
    mangaId = mangaId,
    nameFruit = nameFruit,
    fruitType = fruitType,
    image = image,
    description = description,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp
)