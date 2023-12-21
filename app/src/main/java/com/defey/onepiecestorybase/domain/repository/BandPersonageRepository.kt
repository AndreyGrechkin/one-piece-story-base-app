package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.BandPersonage
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface BandPersonageRepository : SafeApiCall {
    suspend fun syncBandPersonageByPlace(placeId: Int): Response<Unit>
    fun getAllBandPersonage(): Flow<List<BandPersonage>>
    fun getBandPersonage(personageId: Int): Flow<List<BandPersonage>>
    fun getBandPersonageByBand(bandId: Int): Flow<List<BandPersonage>>
}