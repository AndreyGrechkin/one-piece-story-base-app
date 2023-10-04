package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.PlaceDescriptionLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceDescriptionRemoteDataSource
import com.defey.onepiecestorybase.domain.model.PlaceDescription
import com.defey.onepiecestorybase.domain.repository.PlaceDescriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaceDescriptionRepositoryImpl @Inject constructor(
    private val remote: PlaceDescriptionRemoteDataSource,
    private val local: PlaceDescriptionLocalDataSource
) : PlaceDescriptionRepository {
    override fun getAllPlaceDescription(): Flow<List<PlaceDescription>> {
        return local.getAllPlaceDescription().map { list ->
            list.map { it.asDomainModel() }
        }
    }
}