package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.SubjectLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.SubjectRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Subject
import com.defey.onepiecestorybase.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val remote: SubjectRemoteDataSource,
    private val local: SubjectLocalDataSource
) : SubjectRepository {
    override suspend fun syncSubjectByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val subject = remote.getSubjectByPlace(placeId).response
            if (subject.isNotEmpty()) {
                local.addSubject(subject.map { it.toEntity() })
            }
        }
    }

    override fun getAllSubject(): Flow<List<Subject>> {
        return local.getAllSubject().map { list -> list.map { it.asDomainModel() } }
    }

    override fun getSubjectInPlace(placeId: Int): Flow<List<Subject>> {
        return local.getSubjectInPlace(placeId).map { list -> list.map { it.asDomainModel() } }
    }

    override fun getSubject(subjectId: Int): Flow<Subject?> {
        return local.getSubject(subjectId).map { it?.asDomainModel() }
    }

    override suspend fun sendReadSubject(subjectId: Int) {
        local.sendReadSubject(subjectId)
    }

}