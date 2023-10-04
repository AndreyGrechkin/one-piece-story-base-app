package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.Weapons
import com.defey.onepiecestorybase.presentation.utils.Constants.WEAPON_TABLE

@Entity(tableName = WEAPON_TABLE)
data class PersonageWeaponsEntity(
    @PrimaryKey
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val nameWeapons: String?,
    val description: String?,
    val oldWeapon: Boolean,
    val personageDescriptionId: Int?,
)

fun PersonageWeaponsEntity.asDomainModel() = Weapons(
    id = id,
    personageId = personageId,
    mangaId = mangaId,
    nameWeapons = nameWeapons,
    description = description,
    oldWeapon = oldWeapon,
    personageDescriptionId = personageDescriptionId
)