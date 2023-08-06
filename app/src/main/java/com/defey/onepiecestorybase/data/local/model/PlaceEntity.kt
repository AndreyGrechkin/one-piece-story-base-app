package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.PLACE_TABLE

@Entity(tableName = PLACE_TABLE)
data class PlaceEntity(
    @PrimaryKey
    val id: Int,
    val namePlace: String?,
    val country: String?,
    val mangaId: Int,
    val sea: String?,
    val islandType: String?,
    val nameIsland: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
    val timeStep: Long,
)
