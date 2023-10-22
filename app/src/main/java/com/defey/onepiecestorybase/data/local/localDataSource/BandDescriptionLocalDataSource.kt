package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.BandDescriptionDao
import com.defey.onepiecestorybase.data.local.model.BandDescriptionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BandDescriptionLocalDataSource {
    fun getBandDescription(bandId: Int): Flow<List<BandDescriptionEntity>>
    fun getAllBandDescription(): Flow<List<BandDescriptionEntity>>
    suspend fun sendReadBand(bandId: Int)
}

class BandDescriptionLocalDataSourceImpl @Inject constructor(
    private val dao: BandDescriptionDao
) : BandDescriptionLocalDataSource {
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