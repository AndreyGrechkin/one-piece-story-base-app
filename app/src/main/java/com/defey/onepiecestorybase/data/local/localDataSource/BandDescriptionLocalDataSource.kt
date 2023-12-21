package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.BandDescriptionDao
import com.defey.onepiecestorybase.data.local.model.BandDescriptionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BandDescriptionLocalDataSource {
    suspend fun addBandDescription(bandDescriptions: List<BandDescriptionEntity>)
    fun getBandDescription(bandId: Int): Flow<List<BandDescriptionEntity>>
    fun getAllBandDescription(): Flow<List<BandDescriptionEntity>>
    suspend fun sendReadBand(bandId: Int)
}

class BandDescriptionLocalDataSourceImpl @Inject constructor(
    private val dao: BandDescriptionDao
) : BandDescriptionLocalDataSource {
    override suspend fun addBandDescription(bandDescriptions: List<BandDescriptionEntity>) {
        dao.addBandDescription(bandDescriptions)
    }

    override fun getBandDescription(bandId: Int): Flow<List<BandDescriptionEntity>> {
        return dao.getBandDescription(bandId)
    }

    override fun getAllBandDescription(): Flow<List<BandDescriptionEntity>> {
        return dao.getAllBandDescription()
    }

    override suspend fun sendReadBand(bandId: Int) {
        dao.sendReadBand(bandId)
    }

}