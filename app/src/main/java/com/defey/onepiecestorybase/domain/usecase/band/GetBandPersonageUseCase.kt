package com.defey.onepiecestorybase.domain.usecase.band

import com.defey.onepiecestorybase.domain.model.PersonageBandContent
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BandPersonageRepository
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBandPersonageUseCase(
    private val repo: BandPersonageRepository,
    private val personageRepo: PersonageRepository,
    private val personageDescriptionRepo: PersonageDescriptionRepository
) : FlowUseCase<Int?, List<PersonageBandContent>>() {
    override fun execute(parameters: Int?): Flow<Response<List<PersonageBandContent>>> {
        val param = parameters ?: throw NullPointerException("bandId can't be null")
        return repo.getBandPersonageByBand(param).map { bandPersonageList ->
            val personageList =
                personageRepo.getPersonagesById(bandPersonageList.map { it.personageId })
            val personageDescriptionList =
                personageDescriptionRepo.getDescriptionsByPersonageId(personageList.map { it.id })
            val contentList = personageList.map { personage ->
                val personageDescription =
                    personageDescriptionList.filter { it.personageId == personage.id }
                val image = personageDescriptionList.filter { it.personageId == personage.id }
                    .findLast { it.image != null }?.image
                val bandPersonage = bandPersonageList.findLast { it.personageId == personage.id }
                PersonageBandContent(
                    personageId = personage.id,
                    personageName = personage.name,
                    personageImage = image,
                    personageIsFruiting = personageDescription.any { it.fruitId != null },
                    career = bandPersonage?.career,
                    oldPersonage = bandPersonage?.oldPersonage ?: false
                )
            }
            Response.Success(contentList)
        }
    }
}