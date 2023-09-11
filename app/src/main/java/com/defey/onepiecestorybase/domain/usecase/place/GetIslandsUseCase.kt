package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.model.IslandPlace
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.toIslandPlace
import com.defey.onepiecestorybase.domain.repository.IslandRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class GetIslandsUseCase(
    private val repo: IslandRepository,
    private val placeRepo: PlaceRepository
) : FlowUseCase<Nothing?, List<IslandPlace>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<IslandPlace>>> {
        return repo.getIsland().combine(placeRepo.getAllPlaceFlow()){islands, place ->
            Response.Success(islands.map {island ->
                island.toIslandPlace(isEnable = place.any { it.id == island.placeId }) })
        }
    }
}