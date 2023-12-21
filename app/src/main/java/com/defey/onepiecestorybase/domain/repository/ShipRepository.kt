package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import com.defey.onepiecestorybase.domain.model.Ship
import kotlinx.coroutines.flow.Flow

interface ShipRepository : SafeApiCall {
    suspend fun syncShipByPlace(placeId: Int): Response<Unit>
    fun getShipByBand(bandId: Int): Flow<List<Ship>>
}