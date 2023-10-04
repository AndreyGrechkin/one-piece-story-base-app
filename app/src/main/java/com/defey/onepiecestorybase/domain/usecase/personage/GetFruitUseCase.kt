package com.defey.onepiecestorybase.domain.usecase.personage

import com.defey.onepiecestorybase.domain.model.Fruit
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.FruitRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFruitUseCase(
    private val repo: FruitRepository
) : FlowUseCase<Int?, Fruit?>() {
    override fun execute(parameters: Int?): Flow<Response<Fruit?>> {
        val param = parameters ?: throw NullPointerException("personageId can't be null")
        return repo.getFruit(param).map { value ->
            Response.Success(value)
        }
    }
}