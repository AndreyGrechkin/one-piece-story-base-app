package com.defey.onepiecestorybase.domain.usecase.location

import com.defey.onepiecestorybase.domain.repository.PlaceDescriptionRepository

class SendReadLocationUseCase(
    private val repo: PlaceDescriptionRepository
) {
    suspend fun execute(parameters: Int) {
        repo.sendReadLocation(parameters)
    }
}