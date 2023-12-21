package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.BandDescriptionLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandDescriptionRemoteDataSource
import com.defey.onepiecestorybase.domain.model.BandDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BandDescriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BandDescriptionRepositoryImpl @Inject constructor(
    private val remote: BandDescriptionRemoteDataSource,
    private val local: BandDescriptionLocalDataSource
) : BandDescriptionRepository {
    override suspend fun syncBandDescriptionByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val response = remote.getBandDescriptionByPlace(placeId).response
            if (response.isNotEmpty()) {
                local.addBandDescription(response.map { it.toEntity() })
            }
        }
    }

    override fun getBandDescription(bandId: Int): Flow<List<BandDescription>> {
        return local.getBandDescription(bandId).map { list -> list.map { it.asDomainModel() } }
    }

    override fun getAllBandDescription(): Flow<List<BandDescription>> {
        return local.getAllBandDescription().map { list -> list.map { it.asDomainModel() } }
    }

    override suspend fun sendReadBand(bandId: Int) {
        local.sendReadBand(bandId)
    }
}