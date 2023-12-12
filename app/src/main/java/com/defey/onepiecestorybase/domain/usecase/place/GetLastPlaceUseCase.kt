package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.model.Place
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLastPlaceUseCase(
    private val repo: PlaceRepository
) : FlowUseCase<Nothing?, Place?>() {
    override fun execute(parameters: Nothing?): Flow<Response<Place?>> {
        return repo.getLastPlace().map { value -> Response.Success(value) }
    }
}