package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.Ship
import com.defey.onepiecestorybase.presentation.utils.Constants.SHIP_TABLE

@Entity(tableName = SHIP_TABLE)
data class ShipEntity(
    @PrimaryKey
    val id: Int,
    val bandId: Int,
    val mangaId: Int,
    val nameShip: String?,
    val description: String?,
    val oldShip: Int?,
    val nameJp: String?,
    val transcriptionJp: String?,
    val image: String?,
)

fun ShipEntity.asDomainModel() = Ship(
    id = id,
    bandId = bandId,
    mangaId = mangaId,
    nameShip = nameShip,
    description = description,
    oldShip = oldShip,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp,
    image = image
)