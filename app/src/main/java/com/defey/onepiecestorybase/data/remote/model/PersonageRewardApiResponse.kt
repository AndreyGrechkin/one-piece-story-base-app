package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PersonageRewardEntity

data class PersonageRewardApiResponse(
    val response: List<PersonageRewardResponse>
)

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