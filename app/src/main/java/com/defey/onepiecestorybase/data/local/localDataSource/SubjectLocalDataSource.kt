package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.SubjectDao
import com.defey.onepiecestorybase.data.local.model.SubjectEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SubjectLocalDataSource {
    suspend fun addSubject(subjects: List<SubjectEntity>)
    fun getAllSubject(): Flow<List<SubjectEntity>>
    fun getSubjectInPlace(placeId: Int): Flow<List<SubjectEntity>>
    fun getSubject(subjectId: Int): Flow<SubjectEntity?>
    suspend fun sendReadSubject(subjectId: Int)
}

class SubjectLocalDataSourceImpl @Inject constructor(
    private val dao: SubjectDao
) : SubjectLocalDataSource {
    override suspend fun addSubject(subjects: List<SubjectEntity>) {
        dao.addSubject(subjects)
    }

    override fun getAllSubject(): Flow<List<SubjectEntity>> {
        return dao.getAllSubject()
    }

    override fun getSubjectInPlace(placeId: Int): Flow<List<SubjectEntity>> {
        return dao.getSubjectInPlace(placeId)
    }

    override fun getSubject(subjectId: Int): Flow<SubjectEntity?> {
        return dao.getSubject(subjectId)
    }

    override suspend fun sendReadSubject(subjectId: Int) {
        dao.sendReadSubject(subjectId)
    }

}