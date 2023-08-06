package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.FRUIT_TABLE

@Entity(tableName = FRUIT_TABLE)
data class FruitEntity(
    @PrimaryKey
    val id: Int,
    val mangaId: Int,
    val nameFruit: String,
    val fruitType: String?,
    val image: String?,
    val description: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
)
