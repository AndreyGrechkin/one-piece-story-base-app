package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.presentation.utils.Constants.MANGA_TABLE

@Entity(tableName = MANGA_TABLE)
data class MangaEntity(
    @PrimaryKey
    val id: Int,
    val volume: String?,
    val chapter: String?,
    val animeType: String?,
    val animeSeries: String?,
    val description: String?,
    val placeId: Int
)

fun MangaEntity.asDomainModel() = Manga(
    id = id,
    volume = volume,
    chapter = chapter,
    animeType = animeType,
    animeSeries = animeSeries,
    description = description,
    placeId = placeId
)
