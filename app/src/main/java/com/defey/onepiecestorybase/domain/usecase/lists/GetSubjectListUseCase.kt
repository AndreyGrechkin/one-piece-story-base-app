package com.defey.onepiecestorybase.domain.usecase.lists

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SubjectCompact
import com.defey.onepiecestorybase.domain.repository.SubjectRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSubjectListUseCase(
    private val repo: SubjectRepository
) : FlowUseCase<Nothing?, List<SubjectCompact>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<SubjectCompact>>> {
        return repo.getAllSubject().map { list ->
            val compact = list.map {
                SubjectCompact(
                    subjectId = it.id,
                    subjectName = it.name,
                    subjectImage = it.image,
                    isNewSubject = it.isNewSubject
                )
            }
            Response.Success(compact.sortedBy { !it.isNewSubject })
        }
    }
}