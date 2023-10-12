package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Band
import kotlinx.coroutines.flow.Flow

interface BandRepository {
    fun getAllBand(): Flow<List<Band>>
    fun getBands(bandsId: List<Int>): Flow<List<Band>>
    suspend fun getBand(bandId: Int): Band?
    suspend fun getBandList(bandsId: List<Int>): List<Band>
    fun getBandFlow(bandId: Int): Flow<Band>
}