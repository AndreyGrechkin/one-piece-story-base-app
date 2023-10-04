package com.defey.onepiecestorybase.domain.model

data class Reward(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val reward: Int,
    val rewardType: String,
    val image: String?,
)
