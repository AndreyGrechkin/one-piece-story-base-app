package com.defey.onepiecestorybase.domain.usecase.band

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Ship
import com.defey.onepiecestorybase.domain.repository.ShipRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetShipBandUseCase(
    private val repo: ShipRepository
) : FlowUseCase<Int?, List<Ship>>() {
    override fun execute(parameters: Int?): Flow<Response<List<Ship>>> {
        val param = parameters ?: throw NullPointerException("bandId can't be null")
        return repo.getShipByBand(param).map { value ->
            Response.Success(value)
        }
    }
}