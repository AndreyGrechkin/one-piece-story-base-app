package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.BAND_TABLE

@Entity(tableName = BAND_TABLE)
data class BandEntity(
    @PrimaryKey
    val id: Int,
    val mangaId: Int,
    val nameBand: String,
    val image: String?,
    val bandType: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
)
