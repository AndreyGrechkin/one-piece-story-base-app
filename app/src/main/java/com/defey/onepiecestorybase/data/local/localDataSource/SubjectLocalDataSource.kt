package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.InventoryDao
import com.defey.onepiecestorybase.data.local.model.InventoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SubjectLocalDataSource {
    fun getAllSubject(): Flow<List<InventoryEntity>>
    fun getSubjectInPlace(placeId: Int): Flow<List<InventoryEntity>>
    fun getSubject(subjectId: Int): Flow<InventoryEntity?>
    suspend fun sendReadSubject(subjectId: Int)
}

class SubjectLocalDataSourceImpl @Inject constructor(
    private val dao: InventoryDao
) : SubjectLocalDataSource {
    override fun getAllSubject(): Flow<List<InventoryEntity>> {
        return dao.getAllSubject()
    }

    override fun getSubjectInPlace(placeId: Int): Flow<List<InventoryEntity>> {
        return dao.getSubjectInPlace(placeId)
    }

    override fun getSubject(subjectId: Int): Flow<InventoryEntity?> {
        return dao.getSubject(subjectId)
    }

    override suspend fun sendReadSubject(subjectId: Int) {
        dao.sendReadSubject(subjectId)
    }

}