package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.PlaceItemTransit
import com.defey.onepiecestorybase.presentation.utils.Constants.PLACE_TRANSIT_TABLE

@Entity(tableName = PLACE_TRANSIT_TABLE)
data class PlaceTransitItemEntity(
    @PrimaryKey
    val id: Int,
    val mangaId: Int,
    val placeId: Int,
    val personageId: Int,
    val outItemId: Int,
    val inItemId: Int
)

fun PlaceTransitItemEntity.asDomain() = PlaceItemTransit(
    id = id,
    mangaId = mangaId,
    placeId = placeId,
    personageId = personageId,
    outItemId = outItemId,
    inItemId = inItemId
)