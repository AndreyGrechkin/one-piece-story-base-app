package com.defey.onepiecestorybase.domain.usecase.band

import com.defey.onepiecestorybase.domain.model.BandDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BandDescriptionRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBandDescriptionUseCase(
    private val repo: BandDescriptionRepository
) : FlowUseCase<Int?, List<BandDescription>>() {
    override fun execute(parameters: Int?): Flow<Response<List<BandDescription>>> {
        val param = parameters ?: throw NullPointerException("bandId can't be null")
        return repo.getBandDescription(param).map { value ->
            Response.Success(value)
        }
    }
}