package com.defey.onepiecestorybase.domain.usecase.personage

import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository

class SendReadPersonageUseCase(
    private val repo: PersonageDescriptionRepository
) {
    suspend fun execute(parameters: Int) {
        repo.sendReadPersonage(parameters)
    }
}