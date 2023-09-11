package com.defey.onepiecestorybase.domain.model


data class IslandPlace(
    val id: String,
    val placeId: Int?,
    val x: Double,
    val y: Double,
    val isEnabled: Boolean,
    val name: String,
    val nameImage: String
)