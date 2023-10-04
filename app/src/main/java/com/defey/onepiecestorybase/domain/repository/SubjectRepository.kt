package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
    fun getAllSubject(): Flow<List<Subject>>
}