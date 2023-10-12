package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.BandPersonage
import kotlinx.coroutines.flow.Flow

interface BandPersonageRepository {
    fun getAllBandPersonage(): Flow<List<BandPersonage>>
    fun getBandPersonage(personageId: Int): Flow<List<BandPersonage>>
    fun getBandPersonageByBand(bandId: Int): Flow<List<BandPersonage>>
}