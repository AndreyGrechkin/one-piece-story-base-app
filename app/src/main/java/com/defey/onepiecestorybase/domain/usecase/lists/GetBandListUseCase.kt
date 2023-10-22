package com.defey.onepiecestorybase.domain.usecase.lists

import com.defey.onepiecestorybase.domain.model.Band
import com.defey.onepiecestorybase.domain.model.BandCompact
import com.defey.onepiecestorybase.domain.model.BandDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BandDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.BandRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetBandListUseCase(
    private val bandRepo: BandRepository,
    private val bandDescriptionRepo: BandDescriptionRepository
) : FlowUseCase<Nothing?, List<BandCompact>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<BandCompact>>> {
        return bandRepo.getAllBand()
            .combine(bandDescriptionRepo.getAllBandDescription()) { bands, description ->
                val compact = bands.map { band ->
                    BandCompact(
                        bandId = band.id,
                        bandName = band.nameBand,
                        bandType = band.bandType,
                        bandImage = band.image,
                        isNewBand = getNewInfo(band, description)
                    )
                }
                Response.Success(compact.sortedBy { !it.isNewBand })
            }
    }

    private fun getNewInfo(
        band: Band,
        bandDescriptions: List<BandDescription>
    ): Boolean {
        return bandDescriptions.findLast { it.bandId == band.id }?.isNewBand ?: false
    }
}