package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.BAND_PERSONAGE_TABLE

@Entity(tableName = BAND_PERSONAGE_TABLE)
data class BandPersonageEntity(
    @PrimaryKey
    val id: Int,
    val personageId: Int,
    val bandId: Int,
    val mangaId: Int,
    val career: String?,
    val oldPersonage: Boolean
)
