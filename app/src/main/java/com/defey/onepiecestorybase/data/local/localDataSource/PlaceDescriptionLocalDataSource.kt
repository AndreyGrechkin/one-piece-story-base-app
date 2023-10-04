package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PlaceDescriptionDao
import com.defey.onepiecestorybase.data.local.model.PlaceDescriptionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PlaceDescriptionLocalDataSource {
    fun getAllPlaceDescription(): Flow<List<PlaceDescriptionEntity>>
}

class PlaceDescriptionLocalDataSourceImpl @Inject constructor(
    private val dao: PlaceDescriptionDao
) : PlaceDescriptionLocalDataSource {
    override fun getAllPlaceDescription(): Flow<List<PlaceDescriptionEntity>> {
        return dao.getAllDescription()
    }

}