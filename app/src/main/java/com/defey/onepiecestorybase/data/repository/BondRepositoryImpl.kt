package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.BondLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BondRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Bond
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BondRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BondRepositoryImpl @Inject constructor(
    private val remote: BondRemoteDataSource,
    private val local: BondLocalDataSource
) : BondRepository {
    override suspend fun syncBondByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val response = remote.getBondByPlace(placeId).response
            if (response.isNotEmpty()) {
                local.addBond(response.map { it.toEntity() })
            }
        }
    }

    override fun getAllBond(): Flow<List<Bond>> {
        return local.getAllBond().map { list -> list.map { it.asDomainModel() } }
    }
}