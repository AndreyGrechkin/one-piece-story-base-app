package com.defey.onepiecestorybase.domain.model

data class PersonageDescription(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val description: String?,
    val image: String?,
    val personageType: String?,
    val surname: String?,
    val fruitId: Int?,
    val career: String?,
)
