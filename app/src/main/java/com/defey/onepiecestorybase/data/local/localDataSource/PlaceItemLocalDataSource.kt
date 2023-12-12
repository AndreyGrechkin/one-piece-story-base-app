package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PlaceAvatarDao
import com.defey.onepiecestorybase.data.local.database.dao.PlaceItemDao
import com.defey.onepiecestorybase.data.local.database.dao.PlaceTransitItemDao
import com.defey.onepiecestorybase.data.local.model.PlaceAvatarEntity
import com.defey.onepiecestorybase.data.local.model.PlaceItemEntity
import com.defey.onepiecestorybase.data.local.model.PlaceTransitItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PlaceItemLocalDataSource {
    fun getItemsByPlace(placeId: Int): Flow<List<PlaceItemEntity>>
    fun getItemTransitByManga(mangaId: Int): Flow<List<PlaceTransitItemEntity>>
    suspend fun addPlaceAvatar(placeAvatar: List<PlaceAvatarEntity>)
    suspend fun getPlaceAvatar(placeId: Int): List<PlaceAvatarEntity>
    suspend fun getPlaceAvatarById(placeId: Int, avatarId: Int): PlaceAvatarEntity?
}

class PlaceItemLocalDataSourceImpl @Inject constructor(
    private val dao: PlaceItemDao,
    private val daoTransit: PlaceTransitItemDao,
    private val daoAvatar: PlaceAvatarDao
) : PlaceItemLocalDataSource {
    override fun getItemsByPlace(placeId: Int): Flow<List<PlaceItemEntity>> {
        return dao.getItemsByPlace(placeId)
    }

    override fun getItemTransitByManga(mangaId: Int): Flow<List<PlaceTransitItemEntity>> {
        return daoTransit.getItemTransitByManga(mangaId)
    }

    override suspend fun addPlaceAvatar(placeAvatar: List<PlaceAvatarEntity>) {
        daoAvatar.addPlaceAvatar(placeAvatar)
    }

    override suspend fun getPlaceAvatar(placeId: Int): List<PlaceAvatarEntity> {
        return daoAvatar.getPlaceAvatar(placeId)
    }

    override suspend fun getPlaceAvatarById(placeId: Int, avatarId: Int): PlaceAvatarEntity? {
        return daoAvatar.getPlaceAvatarById(placeId, avatarId)
    }

}