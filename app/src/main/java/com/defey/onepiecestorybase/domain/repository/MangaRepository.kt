package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface MangaRepository : SafeApiCall {
    suspend fun syncMangaByPlace(placeId: Int): Response<Unit>
    fun getManga(mangaId: Int): Flow<Manga?>
    fun getMangaInPlace(placeId: Int): Flow<List<Manga>>
}