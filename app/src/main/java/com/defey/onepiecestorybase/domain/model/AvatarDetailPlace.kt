package com.defey.onepiecestorybase.domain.model

data class AvatarDetailPlace(
    val id: Int,
    val avatarId: Int,
    val nameAvatar: String,
    val placeId: Int,
    val mangaId: Int,
    val imageAvatar: String,
    val startPosition: List<Pair<Float, Float>>,
    val endPosition: List<Pair<Float, Float>>,
)
