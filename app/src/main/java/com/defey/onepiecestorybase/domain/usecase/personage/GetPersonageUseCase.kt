package com.defey.onepiecestorybase.domain.usecase.personage

import com.defey.onepiecestorybase.domain.model.Personage
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPersonageUseCase(
    val repo: PersonageRepository
) : FlowUseCase<Int?, Personage>() {
    override fun execute(parameters: Int?): Flow<Response<Personage>> {
        val param = parameters ?: throw NullPointerException("personageId can't be null")
        return repo.getPersonage(param).map { value ->
            Response.Success(value)
        }
    }
}