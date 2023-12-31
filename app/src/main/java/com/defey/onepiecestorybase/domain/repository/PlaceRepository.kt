package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Place
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface PlaceRepository : SafeApiCall {
    suspend fun syncPlaceById(placeId: Int): Response<Unit>
    fun getLastPlace(): Flow<Place?>
    fun getAllPlaceFlow(): Flow<List<Place>>
    fun getLocation(id: Int): Flow<Place>

}