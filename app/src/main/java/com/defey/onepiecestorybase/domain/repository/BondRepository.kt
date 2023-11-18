package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Bond
import kotlinx.coroutines.flow.Flow

interface BondRepository {
    fun getAllBond(): Flow<List<Bond>>
}