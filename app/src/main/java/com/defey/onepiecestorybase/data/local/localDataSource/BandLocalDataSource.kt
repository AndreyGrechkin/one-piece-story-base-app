package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.BandDao
import com.defey.onepiecestorybase.data.local.model.BandEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BandLocalDataSource {
    suspend fun addBand(band: List<BandEntity>)
    fun getAllBand(): Flow<List<BandEntity>>
    fun getBands(bandsId: List<Int>): Flow<List<BandEntity>>
    suspend fun getBand(bandId: Int): BandEntity?
    fun getBandFlow(bandId: Int): Flow<BandEntity>
    suspend fun getBandList(bandsId: List<Int>): List<BandEntity>
}

class BandLocalDataSourceImpl @Inject constructor(
    private val dao: BandDao
) : BandLocalDataSource {
    override suspend fun addBand(band: List<BandEntity>) {
        dao.addBand(band)
    }

    override fun getAllBand(): Flow<List<BandEntity>> {
        return dao.getAllBand()
    }

    override fun getBands(bandsId: List<Int>): Flow<List<BandEntity>> {
        return dao.getBands(bandsId)
    }

    override suspend fun getBand(bandId: Int): BandEntity? {
        return dao.getBand(bandId)
    }

    override fun getBandFlow(bandId: Int): Flow<BandEntity> {
        return dao.getBandFlow(bandId)
    }

    override suspend fun getBandList(bandsId: List<Int>): List<BandEntity> {
        return dao.getBandList(bandsId)
    }
}