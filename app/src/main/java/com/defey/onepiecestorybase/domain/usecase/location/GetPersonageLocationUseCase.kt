package com.defey.onepiecestorybase.domain.usecase.location

import com.defey.onepiecestorybase.domain.model.LocationPersonage
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPersonageLocationUseCase(
    private val repo: PersonageRepository,
    private val repoDescription: PersonageDescriptionRepository
) : FlowUseCase<Int?, List<LocationPersonage>>() {
    override fun execute(parameters: Int?): Flow<Response<List<LocationPersonage>>> {
        val param = parameters ?: throw NullPointerException("locationId can't be null")
        return repo.getPersonageByPlace(param)
            .map { personageList ->
                val description =
                    repoDescription.getDescriptionsByPersonageId(personageList.map { it.id })
                val locationPersonageList = personageList.map { personage ->
                    val personageDescription = description.filter { it.personageId == personage.id }
                    LocationPersonage(
                        personageId = personage.id,
                        personageName = personage.name,
                        personageImage = personageDescription.findLast { it.image != null }?.image,
                        isFruiting = personageDescription.any { it.fruitId != null }
                    )
                }
                Response.Success(locationPersonageList)
            }
    }
}