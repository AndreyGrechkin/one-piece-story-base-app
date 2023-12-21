package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PlaceDescriptionDao
import com.defey.onepiecestorybase.data.local.model.PlaceDescriptionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PlaceDescriptionLocalDataSource {
    suspend fun addPlaceDescription(placeDescription: List<PlaceDescriptionEntity>)
    fun getAllPlaceDescription(): Flow<List<PlaceDescriptionEntity>>
    fun getLocationDescription(placeId: Int): Flow<List<PlaceDescriptionEntity>>
    suspend fun sendReadLocation(locationId: Int)
}

class PlaceDescriptionLocalDataSourceImpl @Inject constructor(
    private val dao: PlaceDescriptionDao
) : PlaceDescriptionLocalDataSource {
    override suspend fun addPlaceDescription(placeDescription: List<PlaceDescriptionEntity>) {
        dao.addPlaceDescription(placeDescription)
    }

    override fun getAllPlaceDescription(): Flow<List<PlaceDescriptionEntity>> {
        return dao.getAllDescription()
    }

    override fun getLocationDescription(placeId: Int): Flow<List<PlaceDescriptionEntity>> {
        return dao.getLocationDescription(placeId)
    }

    override suspend fun sendReadLocation(locationId: Int) {
        dao.sendReadLocation(locationId)
    }

}