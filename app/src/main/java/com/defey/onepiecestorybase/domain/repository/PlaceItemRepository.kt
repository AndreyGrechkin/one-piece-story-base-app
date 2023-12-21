package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.AvatarDetailPlace
import com.defey.onepiecestorybase.domain.model.PlaceItem
import com.defey.onepiecestorybase.domain.model.PlaceItemTransit
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface PlaceItemRepository : SafeApiCall {
    suspend fun syncPlaceItemByPlace(placeId: Int): Response<Unit>
    suspend fun syncPlaceItemTransitByPlace(placeId: Int): Response<Unit>
    fun getItemsByPlace(placeId: Int): Flow<List<PlaceItem>>
    fun getItemTransitByManga(mangaId: Int): Flow<List<PlaceItemTransit>>
    suspend fun addPlaceAvatar(placeAvatar: List<AvatarDetailPlace>)
    suspend fun getPlaceAvatar(placeId: Int): List<AvatarDetailPlace>
    suspend fun getPlaceAvatarById(placeId: Int, avatarId: Int): AvatarDetailPlace?

}