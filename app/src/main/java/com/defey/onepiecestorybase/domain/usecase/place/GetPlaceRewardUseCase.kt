package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.RewardPlace
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.repository.RewardRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetPlaceRewardUseCase(
    private val repoPlace: PlaceRepository,
    private val repoReward: RewardRepository,
    private val repo: PersonageRepository
) : FlowUseCase<Nothing?, List<RewardPlace>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<RewardPlace>>> {
        return combine(
            repoPlace.getLastPlace(),
            repoReward.getAllReward()
        ) { lastPlace, rewardList ->
            val filterReward = rewardList.filter { it.placeId == lastPlace?.id }
            val personageList = repo.getPersonagesById(filterReward.map { it.personageId })
            val rewardPlace = filterReward.map { reward ->
                RewardPlace(
                    id = reward.id,
                    personageId = reward.personageId,
                    mangaId = reward.mangaId,
                    reward = reward.reward,
                    rewardType = reward.rewardType,
                    image = reward.image,
                    placeId = reward.placeId,
                    name = personageList.find { it.id == reward.personageId }?.name.orEmpty()
                )
            }
            Response.Success(rewardPlace)
        }
    }
}