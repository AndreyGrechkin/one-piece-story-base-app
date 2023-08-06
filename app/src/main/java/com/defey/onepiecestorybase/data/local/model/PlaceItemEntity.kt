package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.PLACE_ITEM_TABLE

@Entity(tableName = PLACE_ITEM_TABLE)
data class PlaceItemEntity(
    @PrimaryKey
    val id: Int,
    val placeId: Int,
    val nameItem: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
    val description: String?,
    val image: String?,
)
