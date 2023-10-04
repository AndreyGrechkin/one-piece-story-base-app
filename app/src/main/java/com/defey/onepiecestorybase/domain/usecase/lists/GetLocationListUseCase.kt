package com.defey.onepiecestorybase.domain.usecase.lists

import com.defey.onepiecestorybase.domain.model.LocationCompact
import com.defey.onepiecestorybase.domain.model.Place
import com.defey.onepiecestorybase.domain.model.PlaceDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PlaceDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetLocationListUseCase(
    private val placeRepo: PlaceRepository,
    private val descriptionRepo: PlaceDescriptionRepository
) : FlowUseCase<Nothing?, List<LocationCompact>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<LocationCompact>>> {
        return placeRepo.getAllPlaceFlow()
            .combine(descriptionRepo.getAllPlaceDescription()) { placeList, descriptionList ->
                val placeCompact = placeList.map { getLocationCompact(it, descriptionList) }
                Response.Success(placeCompact)
            }

    }
}

private fun getLocationCompact(place: Place, descriptionList: List<PlaceDescription>): LocationCompact {
    val image = descriptionList
        .filter { it.id == place.id }
        .findLast { it.image != null }?.image
    return LocationCompact(
        locationId = place.id,
        placeName = place.namePlace,
        locationName = place.nameIsland,
        sea = place.sea,
        locationImage = image
    )
}
