package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.BondEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BondApiResponse(
    val response: List<BondResponse>
)

@JsonClass(generateAdapter = true)
data class BondResponse(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val bondPersonageId: Int,
    val description: String?,
    val bondType: String?
)

fun BondResponse.toEntity() = BondEntity(
    id = id,
    personageId = personageId,
    mangaId = mangaId,
    bondPersonageId = bondPersonageId,
    description = description,
    bondType = bondType
)
