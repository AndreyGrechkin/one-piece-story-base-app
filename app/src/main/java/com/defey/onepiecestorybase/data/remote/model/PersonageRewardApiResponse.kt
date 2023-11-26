package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PersonageRewardEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonageRewardApiResponse(
    val response: List<PersonageRewardResponse>
)

@JsonClass(generateAdapter = true)
data class PersonageRewardResponse(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val reward: Int,
    val rewardType: String,
    val image: String?,
    val placeId: Int
)

fun PersonageRewardResponse.toEntity() = PersonageRewardEntity(
    id = id,
    personageId = personageId,
    mangaId = mangaId,
    reward = reward,
    rewardType = rewardType,
    image = image,
    placeId = placeId
)