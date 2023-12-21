package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.PlaceItemLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomain
import com.defey.onepiecestorybase.data.local.model.toEntity
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceItemRemoteDataSource
import com.defey.onepiecestorybase.domain.model.AvatarDetailPlace
import com.defey.onepiecestorybase.domain.model.PlaceItem
import com.defey.onepiecestorybase.domain.model.PlaceItemTransit
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PlaceItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaceItemRepositoryImpl @Inject constructor(
    private val remote: PlaceItemRemoteDataSource,
    private val local: PlaceItemLocalDataSource
) : PlaceItemRepository {
    override suspend fun syncPlaceItemByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val placeItem = remote.getPlaceItemByPlace(placeId).response
            if (placeItem.isNotEmpty()) {
                local.addPlaceItem(placeItem.map { it.toEntity() })
            }
        }
    }

    override suspend fun syncPlaceItemTransitByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val placeTransit = remote.getPlaceItemTransitByPlace(placeId).response
            if (placeTransit.isNotEmpty()) {
                local.addPlaceItemTransit(placeTransit.map { it.toEntity() })
            }
        }
    }

    override fun getItemsByPlace(placeId: Int): Flow<List<PlaceItem>> {
        return local.getItemsByPlace(placeId).map { list -> list.map { it.asDomain() } }
    }

    override fun getItemTransitByManga(mangaId: Int): Flow<List<PlaceItemTransit>> {
        return local.getItemTransitByManga(mangaId).map { list -> list.map { it.asDomain() } }
    }

    override suspend fun addPlaceAvatar(placeAvatar: List<AvatarDetailPlace>) {
        val listAvatar = placeAvatar.map { it.toEntity() }
        local.addPlaceAvatar(listAvatar)
    }

    override suspend fun getPlaceAvatar(placeId: Int): List<AvatarDetailPlace> {
        return local.getPlaceAvatar(placeId).map { it.asDomain() }
    }

    override suspend fun getPlaceAvatarById(placeId: Int, avatarId: Int): AvatarDetailPlace? {
        return local.getPlaceAvatarById(placeId, avatarId)?.asDomain()
    }
}