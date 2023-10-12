package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.data.remote.model.MapResponse
import com.defey.onepiecestorybase.domain.model.Place
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow
import ovh.plrapps.mapcompose.core.TileStreamProvider

interface PlaceRepository: SafeApiCall {
    suspend fun getMapById(id: Int): MapResponse
    suspend fun syncMapById(id: Int): Response<Unit>
    fun getLastPlace(): Flow<Place?>
    fun getAllPlaceFlow(): Flow<List<Place>>
    fun getLocation(id: Int): Flow<Place>

}