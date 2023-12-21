package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Band
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface BandRepository : SafeApiCall {
    suspend fun syncBandByPlace(placeId: Int): Response<Unit>
    fun getAllBand(): Flow<List<Band>>
    fun getBands(bandsId: List<Int>): Flow<List<Band>>
    suspend fun getBand(bandId: Int): Band?
    suspend fun getBandList(bandsId: List<Int>): List<Band>
    fun getBandFlow(bandId: Int): Flow<Band>
}