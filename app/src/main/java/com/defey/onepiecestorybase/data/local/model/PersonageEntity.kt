package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.PERSONAGE_TABLE

@Entity(tableName = PERSONAGE_TABLE)
data class PersonageEntity(
    @PrimaryKey
    val id: Int,
    val mangaId: Int,
    val placeId: Int,
    val name: String,
    val nameJp: String?,
    val transcriptionJp: String?,
    val avatar: String?
)
