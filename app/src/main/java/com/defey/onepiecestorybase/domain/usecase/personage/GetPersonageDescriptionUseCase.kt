package com.defey.onepiecestorybase.domain.usecase.personage

import com.defey.onepiecestorybase.domain.model.PersonageDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPersonageDescriptionUseCase(
    private val repo: PersonageDescriptionRepository
) : FlowUseCase<Int?, List<PersonageDescription>>() {
    override fun execute(parameters: Int?): Flow<Response<List<PersonageDescription>>> {
        val param = parameters ?: throw NullPointerException("personageId can't be null")
        return repo.getPersonageDescription(param).map { value ->
            Response.Success(value)
        }
    }
}