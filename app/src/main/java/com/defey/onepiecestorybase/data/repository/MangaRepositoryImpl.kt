package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.MangaLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.MangaRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val remote: MangaRemoteDataSource,
    private val local: MangaLocalDataSource
) : MangaRepository {
    override suspend fun syncMangaByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val manga = remote.getMangaByPlace(placeId).manges
            if (manga.isNotEmpty()) {
                local.addManga(manga.map { it.toEntity() })
            }
        }
    }

    override fun getManga(mangaId: Int): Flow<Manga?> {
        return local.getManga(mangaId).map { it?.asDomainModel() }
    }

    override fun getMangaInPlace(placeId: Int): Flow<List<Manga>> {
        return local.getMangaInPlace(placeId).map { list -> list.map { it.asDomainModel() } }
    }
}