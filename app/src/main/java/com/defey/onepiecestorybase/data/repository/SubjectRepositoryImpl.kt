package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.SubjectLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.SubjectRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Subject
import com.defey.onepiecestorybase.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val remote: SubjectRemoteDataSource,
    private val local: SubjectLocalDataSource
): SubjectRepository {
    override fun getAllSubject(): Flow<List<Subject>> {
        return local.getAllSubject().map { list -> list.map { it.asDomainModel() } }
    }

}