package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PlaceDao
import com.defey.onepiecestorybase.data.local.model.PlaceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PlaceLocalDataSource {
    fun getLocation(id: Int): Flow<PlaceEntity>
    suspend fun addPlace(place: PlaceEntity)
    fun getLastPlace(): Flow<PlaceEntity?>
    fun getAllPlaceFlow(): Flow<List<PlaceEntity>>
}

class PlaceLocalDataSourceImpl @Inject constructor(
    private val dao: PlaceDao
) : PlaceLocalDataSource {
    override fun getLocation(id: Int): Flow<PlaceEntity> {
        return dao.getLocation(id)
    }

    override suspend fun addPlace(place: PlaceEntity) {
        dao.addPlace(place)
    }

    override fun getLastPlace(): Flow<PlaceEntity?> {
        return dao.getLastPlace()
    }

    override fun getAllPlaceFlow(): Flow<List<PlaceEntity>> {
        return dao.getAllPlaceFlow()
    }

}