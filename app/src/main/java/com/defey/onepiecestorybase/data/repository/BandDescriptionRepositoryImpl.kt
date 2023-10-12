package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.BandDescriptionLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandDescriptionRemoteDataSource
import com.defey.onepiecestorybase.domain.model.BandDescription
import com.defey.onepiecestorybase.domain.repository.BandDescriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BandDescriptionRepositoryImpl @Inject constructor(
    private val remote: BandDescriptionRemoteDataSource,
    private val local: BandDescriptionLocalDataSource
) : BandDescriptionRepository {
    override fun getBandDescription(bandId: Int): Flow<List<BandDescription>> {
        return local.getBandDescription(bandId).map { list -> list.map { it.asDomainModel() } }
    }
}