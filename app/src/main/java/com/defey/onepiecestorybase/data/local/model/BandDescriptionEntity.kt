package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.BAND_DESCRIPTION_TABLE

@Entity(tableName =BAND_DESCRIPTION_TABLE)
data class BandDescriptionEntity(
    @PrimaryKey
    val id: Int,
    val bandId: Int,
    val mangaId: Int,
    val description: String?,
)
