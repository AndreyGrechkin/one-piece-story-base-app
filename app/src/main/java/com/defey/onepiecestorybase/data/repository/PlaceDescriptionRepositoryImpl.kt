package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.PlaceDescriptionLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceDescriptionRemoteDataSource
import com.defey.onepiecestorybase.domain.model.PlaceDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PlaceDescriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaceDescriptionRepositoryImpl @Inject constructor(
    private val remote: PlaceDescriptionRemoteDataSource,
    private val local: PlaceDescriptionLocalDataSource
) : PlaceDescriptionRepository {
    override suspend fun syncPlaceDescriptionByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val description = remote.getPlaceDescriptionByPlace(placeId).response
            if (description.isNotEmpty()) {
                local.addPlaceDescription(description.map { it.toEntity() })
            }
        }
    }

    override fun getAllPlaceDescription(): Flow<List<PlaceDescription>> {
        return local.getAllPlaceDescription().map { list ->
            list.map { it.asDomainModel() }
        }
    }

    override fun getLocationDescription(placeId: Int): Flow<List<PlaceDescription>> {
        return local.getLocationDescription(placeId).map { list ->
            list.map { it.asDomainModel() }
        }
    }

    override suspend fun sendReadLocation(locationId: Int) {
        local.sendReadLocation(locationId)
    }
}