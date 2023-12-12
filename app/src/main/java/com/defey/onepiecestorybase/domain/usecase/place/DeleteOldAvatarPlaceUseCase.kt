package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.repository.IslandRepository

class DeleteOldAvatarPlaceUseCase(
    private val repo: IslandRepository
) {
    suspend fun execute(lastPlaceId: Int, avatarName: String) {
        repo.deleteOldAvatarPlace(lastPlaceId, avatarName)
    }
}
