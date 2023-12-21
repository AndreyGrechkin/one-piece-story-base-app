package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import com.defey.onepiecestorybase.domain.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository : SafeApiCall {
    suspend fun syncSubjectByPlace(placeId: Int): Response<Unit>
    fun getAllSubject(): Flow<List<Subject>>
    fun getSubjectInPlace(placeId: Int): Flow<List<Subject>>
    fun getSubject(subjectId: Int): Flow<Subject?>
    suspend fun sendReadSubject(subjectId: Int)
}