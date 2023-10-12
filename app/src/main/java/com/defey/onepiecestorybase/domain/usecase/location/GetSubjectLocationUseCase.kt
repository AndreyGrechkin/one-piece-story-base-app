package com.defey.onepiecestorybase.domain.usecase.location

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Subject
import com.defey.onepiecestorybase.domain.repository.SubjectRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSubjectLocationUseCase(
    private val repo: SubjectRepository
) : FlowUseCase<Int?, List<Subject>>() {
    override fun execute(parameters: Int?): Flow<Response<List<Subject>>> {
        val param = parameters ?: throw NullPointerException("locationId can't be null")
        return repo.getSubjectInPlace(param).map { value ->
            Response.Success(value)
        }
    }
}