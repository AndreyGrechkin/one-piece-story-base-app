package com.defey.onepiecestorybase.domain.usecase.place

import android.util.Log
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.AvatarPlace
import com.defey.onepiecestorybase.domain.repository.IslandRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetAvatarPlaceUseCase(
    private val repo: IslandRepository
) : FlowUseCase<Int, List<AvatarPlace>>() {
    override fun execute(parameters: Int?): Flow<Response<List<AvatarPlace>>> {
        val param = parameters ?: throw Exception("Place Id can't be null")
        return repo.getPersonageIsland(param)
            .combine(repo.getIslandTransit(param)) { avatarList, transit ->
                val list = avatarList.map { avatar ->
                    val coordinateList = transit.filter { avatar.id == it.avatarId }
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