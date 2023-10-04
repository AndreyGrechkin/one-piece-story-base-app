package com.defey.onepiecestorybase.domain.usecase.lists

import com.defey.onepiecestorybase.domain.model.BandCompact
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.toBandCompact
import com.defey.onepiecestorybase.domain.repository.BandRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBandListUseCase(
    private val bandRepo: BandRepository
) : FlowUseCase<Nothing?, List<BandCompact>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<BandCompact>>> {
        return bandRepo.getAllBand()
            .map { bands ->
                val compact = bands.map { it.toBandCompact() }
                Response.Success(compact)
            }
    }
}