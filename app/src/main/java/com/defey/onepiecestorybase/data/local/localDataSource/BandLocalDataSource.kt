package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.BandDao
import com.defey.onepiecestorybase.data.local.model.BandEntity
import com.defey.onepiecestorybase.domain.model.Band
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BandLocalDataSource {
    fun getAllBand(): Flow<List<BandEntity>>
    fun getBands(bandsId: List<Int>): Flow<List<BandEntity>>
    suspend fun getBand(bandId: Int): BandEntity?
    suspend fun getBandList(bandsId: List<Int>): List<BandEntity>
}

class BandLocalDataSourceImpl @Inject constructor(
    private val dao: BandDao
) : BandLocalDataSource {
    override fun getAllBand(): Flow<List<BandEntity>> {
        return dao.getAllBand()
    }

    override fun getBands(bandsId: List<Int>): Flow<List<BandEntity>> {
        return dao.getBands(bandsId)
    }

    override suspend fun getBand(bandId: Int): BandEntity? {
        return dao.getBand(bandId)
    }

    override suspend fun getBandList(bandsId: List<Int>): List<BandEntity> {
        return dao.getBandList(bandsId)
    }


}