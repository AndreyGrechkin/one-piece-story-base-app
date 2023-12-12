package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.InventoryEntity

data class InventoryApiResponse(
    val response: List<InventoryResponse>
)

data class InventoryResponse(
    val id: Int,
    val mangaId: Int,
    val placeId: Int,
    val image: String?,
    val description: String?,
    val name: String,
    val nameJp: String?,
    val transcriptionJp: String?,
)

fun InventoryResponse.toEntity() = InventoryEntity(
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