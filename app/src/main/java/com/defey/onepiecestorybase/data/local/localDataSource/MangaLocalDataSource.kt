package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.MangaDao
import com.defey.onepiecestorybase.data.local.model.MangaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MangaLocalDataSource {
    suspend fun addManga(manga: List<MangaEntity>)
    fun getManga(mangaId: Int): Flow<MangaEntity?>
    fun getMangaInPlace(placeId: Int): Flow<List<MangaEntity>>
}

class MangaLocalDataSourceImpl @Inject constructor(
    private val dao: MangaDao
) : MangaLocalDataSource {
    override suspend fun addManga(manga: List<MangaEntity>) {
        dao.addManga(manga)
    }

    override fun getManga(mangaId: Int): Flow<MangaEntity?> {
        return dao.getManga(mangaId)
    }

    override fun getMangaInPlace(placeId: Int): Flow<List<MangaEntity>> {
        return dao.getMangaInPlace(placeId)
    }

}