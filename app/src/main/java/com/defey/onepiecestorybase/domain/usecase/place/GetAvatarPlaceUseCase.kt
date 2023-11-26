package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.model.AvatarPlace
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.IslandRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetAvatarPlaceUseCase(
    private val repo: IslandRepository,
    private val repoPlace: PlaceRepository
) : FlowUseCase<Nothing?, List<AvatarPlace>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<AvatarPlace>>> {
        return combine(
            repoPlace.getLastPlace(),
            repo.getPersonageIsland(),
            repo.getIslandTransit()
        )
        { lastPlace, avatarList, transit ->
            val lastPlaceId = lastPlace?.id ?: 1
            val avatarListPlace = avatarList.filter { it.placeId == lastPlaceId }
            val transitPlace = transit.filter { it.placeId == lastPlaceId }
            val list = avatarListPlace.map { avatar ->
                val coordinateList = transitPlace.filter { avatar.id == it.avatarId }
                    .map { Pair(it.posX, it.posY) }
                AvatarPlace(
                    id = avatar.nameAvatar,
                    placeId = avatar.placeId,
                    name = avatar.nameAvatar,
                    nameImage = avatar.nameImage,
                    coordinate = Pair(avatar.posX, avatar.posY),
                    coordinateList = coordinateList
                )
            }
            Response.Success(list)
        }
    }
}