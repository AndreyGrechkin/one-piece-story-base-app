package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.PersonageIsland
import com.defey.onepiecestorybase.presentation.utils.Constants.PERSONAGE_ISLAND_TABLE

@Entity(tableName = PERSONAGE_ISLAND_TABLE)
data class PersonageIslandEntity(
    @PrimaryKey val id: Int,
    val placeId: Int,
    val nameAvatar: String,
    val nameImage: String,
    val posX: Double,
    val posY: Double,
)

fun PersonageIslandEntity.asDomain() = PersonageIsland(
    id = id,
    placeId = placeId,
    nameAvatar = nameAvatar,
    nameImage = nameImage,
    posX = posX,
    posY = posY
)
