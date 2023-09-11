package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.IslandTransit
import com.defey.onepiecestorybase.presentation.utils.Constants.ISLAND_TRANSIT_TABLE

@Entity(
    tableName = ISLAND_TRANSIT_TABLE,
    foreignKeys = [ForeignKey(
        entity = PersonageIslandEntity::class,
        parentColumns = ["id"],
        childColumns = ["placeId"],
        onDelete = CASCADE
    )]
)
data class IslandTransitEntity(
    @PrimaryKey val id: Int,
    val avatarId: Int,
    val placeId: Int,
    val posX: Double,
    val posY: Double
)

fun IslandTransitEntity.asDomain() = IslandTransit(
    id = id,
    avatarId = avatarId,
    placeId = placeId,
    posX = posX,
    posY = posY
)
