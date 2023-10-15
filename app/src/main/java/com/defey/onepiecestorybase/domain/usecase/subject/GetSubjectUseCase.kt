package com.defey.onepiecestorybase.domain.usecase.subject

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Subject
import com.defey.onepiecestorybase.domain.repository.SubjectRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSubjectUseCase(
    private val repo: SubjectRepository
) : FlowUseCase<Int?, Subject>() {
    override fun execute(parameters: Int?): Flow<Response<Subject>> {
        val param = parameters ?: throw NullPointerException("subjectId can't be null")
        return repo.getSubject(param).map { value ->
            value?.let { Response.Success(it) } ?: Response.Failure(
                false,
                null,
                "subject is not found"
            )
        }
    }
}