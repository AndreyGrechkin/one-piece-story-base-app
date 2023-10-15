package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
    fun getAllSubject(): Flow<List<Subject>>
    fun getSubjectInPlace(placeId: Int): Flow<List<Subject>>
    fun getSubject(subjectId: Int): Flow<Subject?>
}