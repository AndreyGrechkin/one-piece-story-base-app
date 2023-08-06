package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.INVENTORY_TABLE

@Entity(tableName = INVENTORY_TABLE)
data class InventoryEntity(
    @PrimaryKey
    val id: Int,
    val mangaId: Int,
    val placeId: Int,
    val image: String?,
    val description: String?,
    val name: String,
    val nameJp: String?,
    val transcriptionJp: String?,
)
