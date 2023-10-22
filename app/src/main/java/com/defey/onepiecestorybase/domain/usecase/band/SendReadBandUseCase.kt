package com.defey.onepiecestorybase.domain.usecase.band

import com.defey.onepiecestorybase.domain.repository.BandDescriptionRepository

class SendReadBandUseCase(
    private val repo: BandDescriptionRepository
) {
    suspend fun execute(parameters: Int) {
        repo.sendReadBand(parameters)
    }
}