package com.defey.onepiecestorybase.domain.usecase.personage

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Weapons
import com.defey.onepiecestorybase.domain.repository.WeaponsRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWeaponsUseCase(
    private val repo: WeaponsRepository
) : FlowUseCase<Int?, List<Weapons>>() {
    override fun execute(parameters: Int?): Flow<Response<List<Weapons>>> {
        val param = parameters ?: throw NullPointerException("personageId can't be null")
        return repo.getWeapons(param).map { value ->
            val response = value.filter { item ->
                value.none { it.personageDescriptionId == item.id }
            }
            Response.Success(response)
        }
    }
}