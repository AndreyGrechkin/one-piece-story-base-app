package com.defey.onepiecestorybase.domain.model

data class AvatarPlace(
    val id: String,
    val placeId: Int,
    val name: String,
    val nameImage: String,
    var coordinate: Pair<Double, Double>,
    var rotateAvatar: Float = 0f,
    var inclineAvatar: Float = 0f,
    val coordinateList: List<Pair<Double, Double>>
)
