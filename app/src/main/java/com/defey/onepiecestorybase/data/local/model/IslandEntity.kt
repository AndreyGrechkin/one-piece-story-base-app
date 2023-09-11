package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.Island
import com.defey.onepiecestorybase.domain.model.IslandPlace
import com.defey.onepiecestorybase.presentation.utils.Constants.ISLAND_TABLE

@Entity(tableName = ISLAND_TABLE)
data class IslandEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val nameImage: String,
    val posX: Double,
    val posY: Double,
    val placeId: Int?
)

fun IslandEntity.asDomain() = Island(
    id = id,
    name = name,
    nameImage = nameImage,
    posX = posX,
    posY = posY,
    placeId = placeId
)
