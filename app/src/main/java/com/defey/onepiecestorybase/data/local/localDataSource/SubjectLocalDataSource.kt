package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.InventoryDao
import com.defey.onepiecestorybase.data.local.model.InventoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SubjectLocalDataSource {
    fun getAllSubject(): Flow<List<InventoryEntity>>
}

class SubjectLocalDataSourceImpl @Inject constructor(
    private val dao: InventoryDao
): SubjectLocalDataSource {
    override fun getAllSubject(): Flow<List<InventoryEntity>> {
        return dao.getAllSubject()
    }

}