package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.BandPersonageDao
import com.defey.onepiecestorybase.data.local.model.BandPersonageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BandPersonageLocalDataSource {
    suspend fun addBandPPersonage(bandPersonages: List<BandPersonageEntity>)
    fun getAllBandPersonage(): Flow<List<BandPersonageEntity>>
    fun getBandPersonage(personageId: Int): Flow<List<BandPersonageEntity>>
    fun getBandPersonageByBand(bandId: Int): Flow<List<BandPersonageEntity>>

}

class BandPersonageLocalDataSourceImpl @Inject constructor(
    private val dao: BandPersonageDao
) : BandPersonageLocalDataSource {
    override suspend fun addBandPPersonage(bandPersonages: List<BandPersonageEntity>) {
        dao.addBandPersonage(bandPersonages)
    }

    override fun getAllBandPersonage(): Flow<List<BandPersonageEntity>> {
        return dao.getAllBandPersonage()
    }

    override fun getBandPersonage(personageId: Int): Flow<List<BandPersonageEntity>> {
        return dao.getBandPersonage(personageId)
    }

    override fun getBandPersonageByBand(bandId: Int): Flow<List<BandPersonageEntity>> {
        return dao.getBandPersonageByBand(bandId)
    }

}