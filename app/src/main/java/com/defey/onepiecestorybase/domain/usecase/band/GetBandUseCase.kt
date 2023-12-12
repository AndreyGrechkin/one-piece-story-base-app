package com.defey.onepiecestorybase.domain.usecase.band

import com.defey.onepiecestorybase.domain.model.Band
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BandRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBandUseCase(
    private val repo: BandRepository
) : FlowUseCase<Int?, Band>() {
    override fun execute(parameters: Int?): Flow<Response<Band>> {
        val param = parameters ?: throw NullPointerException("bandId can't be null")
        return repo.getBandFlow(param).map { value ->
            Response.Success(value)
        }
    }
}