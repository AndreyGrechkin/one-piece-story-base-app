package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.BandLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Band
import com.defey.onepiecestorybase.domain.repository.BandRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BandRepositoryImpl @Inject constructor(
    private val remote: BandRemoteDataSource,
    private val local: BandLocalDataSource
) : BandRepository {
    override fun getAllBand(): Flow<List<Band>> {
        return local.getAllBand().map { list -> list.map { it.asDomainModel() } }
    }

    override fun getBands(bandsId: List<Int>): Flow<List<Band>> {
        return local.getBands(bandsId).map { list -> list.map { it.asDomainModel() } }
    }

    override suspend fun getBand(bandId: Int): Band? {
        return local.getBand(bandId)?.asDomainModel()
    }

    override suspend fun getBandList(bandsId: List<Int>): List<Band> {
       return local.getBandList(bandsId).map { it.asDomainModel() }
    }

}