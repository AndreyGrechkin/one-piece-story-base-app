package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.BandDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface BandDescriptionRepository : SafeApiCall {
    suspend fun syncBandDescriptionByPlace(placeId: Int): Response<Unit>
    fun getBandDescription(bandId: Int): Flow<List<BandDescription>>
    fun getAllBandDescription(): Flow<List<BandDescription>>
    suspend fun sendReadBand(bandId: Int)
}