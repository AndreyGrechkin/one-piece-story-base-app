package com.defey.onepiecestorybase.domain.usecase.fruit

import com.defey.onepiecestorybase.domain.repository.FruitRepository

class SendReadFruitUseCase(
    private val repo: FruitRepository
) {

    suspend fun execute(parameters: Int) {
        repo.sendReadFruit(parameters)
    }
}