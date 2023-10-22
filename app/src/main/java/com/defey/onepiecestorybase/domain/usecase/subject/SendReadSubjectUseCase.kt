package com.defey.onepiecestorybase.domain.usecase.subject

import com.defey.onepiecestorybase.domain.repository.SubjectRepository

class SendReadSubjectUseCase(
    private val repo: SubjectRepository
) {
    suspend fun execute(parameters: Int) {
        repo.sendReadSubject(parameters)
    }
}