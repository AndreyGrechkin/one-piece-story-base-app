package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Bond
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface BondRepository : SafeApiCall {
    suspend fun syncBondByPlace(placeId: Int): Response<Unit>
    fun getAllBond(): Flow<List<Bond>>
}