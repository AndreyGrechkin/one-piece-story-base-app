package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.PlaceDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface PlaceDescriptionRepository : SafeApiCall {
    suspend fun syncPlaceDescriptionByPlace(placeId: Int): Response<Unit>
    fun getAllPlaceDescription(): Flow<List<PlaceDescription>>
    fun getLocationDescription(placeId: Int): Flow<List<PlaceDescription>>
    suspend fun sendReadLocation(locationId: Int)
}