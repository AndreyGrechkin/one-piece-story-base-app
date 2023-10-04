package com.defey.onepiecestorybase.domain.model

data class Weapons(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val nameWeapons: String?,
    val description: String?,
    val oldWeapon: Boolean,
    val personageDescriptionId: Int?,
)
