package com.defey.onepiecestorybase.domain.usecase.lists

import com.defey.onepiecestorybase.domain.model.FruitCompact
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.FruitRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFruitListUseCase(
    private val repo: FruitRepository
) : FlowUseCase<Nothing?, List<FruitCompact>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<FruitCompact>>> {
        return repo.getAllFruit().map { list ->
            val compact = list.map {
                FruitCompact(
                    fruitId = it.id,
                    fruitName = it.nameFruit,
                    fruitType = it.fruitType,
                    fruitImage = it.image
                )
            }
            Response.Success(compact)
        }
    }
}