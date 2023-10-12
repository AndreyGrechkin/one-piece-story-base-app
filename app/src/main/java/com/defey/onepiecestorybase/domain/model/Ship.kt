package com.defey.onepiecestorybase.domain.model

data class Ship(
    val id: Int,
    val bandId: Int,
    val mangaId: Int,
    val nameShip: String?,
    val description: String?,
    val oldShip: Boolean,
    val nameJp: String?,
    val transcriptionJp: String?,
    val image: String?,
)
