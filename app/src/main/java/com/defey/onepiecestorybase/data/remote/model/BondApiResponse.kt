package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.BondEntity

data class BondApiResponse(
    val response: List<BondResponse>
)

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
