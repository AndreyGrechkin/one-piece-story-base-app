package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.PLACE_DESCRIPTION_TABLE

@Entity(tableName = PLACE_DESCRIPTION_TABLE)
data class PlaceDescriptionEntity(
    @PrimaryKey
    val id: Int,
    val placeId: Int,
    val mangaId: Int,
    val description: String?,
    val event: String?,
    val image: String?,
)
