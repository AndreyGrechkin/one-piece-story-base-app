package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Ship
import kotlinx.coroutines.flow.Flow

interface ShipRepository {
    fun getShipByBand(bandId: Int): Flow<List<Ship>>
}