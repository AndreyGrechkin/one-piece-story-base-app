package com.defey.onepiecestorybase.domain.model

data class RewardPlace(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val reward: Int,
    val rewardType: String,
    val image: String?,
    val placeId: Int,
    val name: String
)
