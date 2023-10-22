package com.defey.onepiecestorybase.domain.model

data class Fruit(
    val id: Int,
    val mangaId: Int,
    val nameFruit: String,
    val fruitType: String?,
    val image: String?,
    val description: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
    val isNewFruit: Boolean
)
