package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.ShipDao
import com.defey.onepiecestorybase.data.local.model.ShipEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ShipLocalDataSource {
    suspend fun addShip(ships: List<ShipEntity>)
    fun getShipByBand(bandId: Int): Flow<List<ShipEntity>>
}

class ShipLocalDataSourceImpl @Inject constructor(
    private val dao: ShipDao
) : ShipLocalDataSource {
    override suspend fun addShip(ships: List<ShipEntity>) {
        dao.addShip(ships)
    }

    override fun getShipByBand(bandId: Int): Flow<List<ShipEntity>> {
        return dao.getShipByBand(bandId)
    }
}