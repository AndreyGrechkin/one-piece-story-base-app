package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.PlaceLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomain
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Place
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val remote: PlaceRemoteDataSource,
    private val local: PlaceLocalDataSource
) : PlaceRepository {
    override suspend fun syncPlaceById(placeId: Int): Response<Unit> {
        return safeApiCall {
            val place = remote.getPLaceById(placeId)
            local.addPlace(place.toEntity())
        }
    }

    override fun getLastPlace(): Flow<Place?> {
        return local.getLastPlace().map { it?.asDomain() }
    }

    override fun getAllPlaceFlow(): Flow<List<Place>> {
        return local.getAllPlaceFlow().map { value -> value.map { it.asDomain() } }
    }

    override fun getLocation(id: Int): Flow<Place> {
        return local.getLocation(id).map { it.asDomain() }
    }
}