package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.PERSONAGE_DESCRIPTION_TABLE

@Entity(tableName = PERSONAGE_DESCRIPTION_TABLE)
data class PersonageDescriptionEntity(
    @PrimaryKey
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val description: String?,
    val image: String?,
    val personageType: String?,
    val surname: String?,
    val fruitId: Int?,
    val career: String?,
)
