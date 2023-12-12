package com.defey.onepiecestorybase.domain.usecase.fruit

import com.defey.onepiecestorybase.domain.model.Fruit
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.FruitRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFruitUseCase(
    private val repo: FruitRepository
) : FlowUseCase<Int?, Fruit>() {
    override fun execute(parameters: Int?): Flow<Response<Fruit>> {
        val param = parameters ?: throw NullPointerException("fruitId can't be null")
        return repo.getFruit(param).map { value ->
            value?.let { Response.Success(it) } ?: Response.Failure(
                false,
                null,
                errorMessage = "fruit is not found"
            )
        }
    }
}