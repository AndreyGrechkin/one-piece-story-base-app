package com.defey.onepiecestorybase.domain.usecase.location

import com.defey.onepiecestorybase.domain.model.PlaceDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PlaceDescriptionRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLocationDescriptionUseCase(
    private val repo: PlaceDescriptionRepository
) : FlowUseCase<Int?, List<PlaceDescription>>() {
    override fun execute(parameters: Int?): Flow<Response<List<PlaceDescription>>> {
        val param = parameters ?: throw NullPointerException("locationId can't be null")
        return repo.getLocationDescription(param).map { value ->
            Response.Success(value)
        }
    }
}