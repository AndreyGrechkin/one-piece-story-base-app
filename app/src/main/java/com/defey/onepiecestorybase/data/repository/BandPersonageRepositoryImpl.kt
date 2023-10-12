package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.BandPersonageLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandPersonageRemoteDataSource
import com.defey.onepiecestorybase.domain.model.BandPersonage
import com.defey.onepiecestorybase.domain.repository.BandPersonageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BandPersonageRepositoryImpl @Inject constructor(
    private val remote: BandPersonageRemoteDataSource,
    private val local: BandPersonageLocalDataSource
) : BandPersonageRepository {
    override fun getAllBandPersonage(): Flow<List<BandPersonage>> {
        return local.getAllBandPersonage()
            .map { list -> list.map { it.asDomainModel() } }
    }

    override fun getBandPersonage(personageId: Int): Flow<List<BandPersonage>> {
        return local.getBandPersonage(personageId)
            .map { list -> list.map { it.asDomainModel() } }
    }

    override fun getBandPersonageByBand(bandId: Int): Flow<List<BandPersonage>> {
        return local.getBandPersonageByBand(bandId)
            .map { list -> list.map { it.asDomainModel() } }
    }
}