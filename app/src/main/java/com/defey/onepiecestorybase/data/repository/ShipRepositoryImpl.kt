package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.ShipLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.ShipRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Ship
import com.defey.onepiecestorybase.domain.repository.ShipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShipRepositoryImpl @Inject constructor(
    private val remote: ShipRemoteDataSource,
    private val local: ShipLocalDataSource
) : ShipRepository {
    override suspend fun syncShipByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val ship = remote.getShipByPlace(placeId).response
            if (ship.isNotEmpty()) {
                local.addShip(ship.map { it.toEntity() })
            }
        }
    }

    override fun getShipByBand(bandId: Int): Flow<List<Ship>> {
        return local.getShipByBand(bandId).map { list -> list.map { it.asDomainModel() } }
    }
}