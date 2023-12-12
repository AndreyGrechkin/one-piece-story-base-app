package com.defey.onepiecestorybase.domain.usecase.actionPlace

import com.defey.onepiecestorybase.domain.model.AvatarDetailPlace
import com.defey.onepiecestorybase.domain.model.PlaceItem
import com.defey.onepiecestorybase.domain.model.PlaceItemTransit
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.repository.PlaceItemRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import com.defey.onepiecestorybase.presentation.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetPlaceAvatarUseCase(
    private val repoItem: PlaceItemRepository,
    private val repoPersonage: PersonageRepository,

    ) : FlowUseCase<GetPlaceAvatar?, List<AvatarDetailPlace>>() {
    override fun execute(parameters: GetPlaceAvatar?): Flow<Response<List<AvatarDetailPlace>>> {
        val param = parameters ?: throw NullPointerException("locationId can't be null")
        return combine(
            repoItem.getItemsByPlace(param.placeId),
            repoItem.getItemTransitByManga(param.mangaId)
        ) { listItem, listTransit ->
            val personageIdList = listTransit.map { it.personageId }.distinct()
            val personageList = repoPersonage.getPersonagesById(personageIdList)
            val lastAvatar = param.lastAvatar
                .filter { avatarDetail ->
                    personageList.none { it.id == avatarDetail.avatarId }
                }
                .map { lastAvatar ->
                    AvatarDetailPlace(
                        id = lastAvatar.id,
                        avatarId = lastAvatar.avatarId,
                        nameAvatar = lastAvatar.nameAvatar,
                        placeId = param.placeId,
                        mangaId = param.mangaId,
                        imageAvatar = lastAvatar.imageAvatar,
                        startPosition = listOf(lastAvatar.endPosition.last()),
                        endPosition = listOf(lastAvatar.endPosition.last())
                    )
                }
            val avatarList = personageList.map { personage ->
                val daoAvatar = repoItem.getPlaceAvatarById(param.placeId, personage.id)
                val personageTransit = listTransit.filter { it.personageId == personage.id }
                AvatarDetailPlace(
                    id = daoAvatar?.id ?: 0,
                    avatarId = personage.id,
                    nameAvatar = personage.name,
                    placeId = param.placeId,
                    mangaId = param.mangaId,
                    imageAvatar = Constants.PATH_ASSETS_AVATAR + personage.avatar,
                    startPosition = getPosition(listItem, TypeTransit.START, personageTransit),
                    endPosition = getPosition(listItem, TypeTransit.END, personageTransit)
                )
            }

            repoItem.addPlaceAvatar(avatarList + lastAvatar)
            val avatarDetail = repoItem.getPlaceAvatar(param.placeId)
            Response.Success(avatarDetail)
        }
    }

    private fun getPosition(
        listItem: List<PlaceItem>,
        typeTransit: TypeTransit,
        personageTransit: List<PlaceItemTransit>
    ): List<Pair<Float, Float>> {
        return personageTransit.map { transit ->
            val type = when (typeTransit) {
                TypeTransit.START -> transit.outItemId
                TypeTransit.END -> transit.inItemId
            }
            val pairTransit = listItem.first { it.id == type }
            Pair(first = pairTransit.posX.toFloat(), second = pairTransit.posY.toFloat())
        }
    }
}

data class GetPlaceAvatar(
    val placeId: Int,
    val mangaId: Int,
    val lastAvatar: List<AvatarDetailPlace>
)

enum class TypeTransit {
    START, END
}