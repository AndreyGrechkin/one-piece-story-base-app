package com.defey.onepiecestorybase.data.remote.model

import com.defey.onepiecestorybase.data.local.model.PersonageWeaponsEntity

data class PersonageWeaponsApiResponse(
    val response: List<PersonageWeaponsResponse>
)

data class PersonageWeaponsResponse(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val nameWeapons: String?,
    val description: String?,
    val oldWeapon: Boolean,
    val personageDescriptionId: Int?,
)

fun PersonageWeaponsResponse.toEntity() = PersonageWeaponsEntity(
    id = id,
    personageId = personageId,
    mangaId = mangaId,
    nameWeapons = nameWeapons,
    description = description,
    oldWeapon = oldWeapon,
    personageDescriptionId = personageDescriptionId
)