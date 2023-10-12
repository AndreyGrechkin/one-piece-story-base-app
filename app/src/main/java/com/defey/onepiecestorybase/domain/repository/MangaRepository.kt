package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    fun getManga(mangaId: Int): Flow<Manga?>
    fun getMangaInPlace(placeId: Int): Flow<List<Manga>>
}