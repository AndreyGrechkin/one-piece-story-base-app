package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.MangaLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.MangaRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val remote: MangaRemoteDataSource,
    private val local: MangaLocalDataSource
) : MangaRepository {
    override fun getManga(mangaId: Int): Flow<Manga?> {
        return local.getManga(mangaId).map { it?.asDomainModel() }
    }
}