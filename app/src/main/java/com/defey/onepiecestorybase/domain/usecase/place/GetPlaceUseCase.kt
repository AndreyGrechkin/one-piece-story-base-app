package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.model.Place
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPlaceUseCase(
    private val repo: PlaceRepository
) : FlowUseCase<Int?, Place>() {
    override fun execute(parameters: Int?): Flow<Response<Place>> {
        val param = parameters ?: throw NullPointerException("placeId can't be null")
        return repo.getLocation(param).map {
            Response.Success(it)
        }
    }
}