package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.BandDescription
import kotlinx.coroutines.flow.Flow

interface BandDescriptionRepository {
    fun getBandDescription(bandId: Int): Flow<List<BandDescription>>
}