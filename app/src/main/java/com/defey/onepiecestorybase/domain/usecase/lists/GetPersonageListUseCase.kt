package com.defey.onepiecestorybase.domain.usecase.lists

import com.defey.onepiecestorybase.domain.model.Band
import com.defey.onepiecestorybase.domain.model.BandPersonage
import com.defey.onepiecestorybase.domain.model.Personage
import com.defey.onepiecestorybase.domain.model.PersonageCompact
import com.defey.onepiecestorybase.domain.model.PersonageDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BandPersonageRepository
import com.defey.onepiecestorybase.domain.repository.BandRepository
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetPersonageListUseCase(
    private val personageRepo: PersonageRepository,
    private val personageDescriptionRepo: PersonageDescriptionRepository,
    private val bandRepo: BandRepository,
    private val bandPersonageRepo: BandPersonageRepository
) : FlowUseCase<Nothing?, List<PersonageCompact>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<PersonageCompact>>> {
        return combine(
            personageRepo.getPersonageList(),
            personageDescriptionRepo.getAllPersonageDescription(),
            bandRepo.getAllBand(),
            bandPersonageRepo.getAllBandPersonage()
        ) { personages, personageDescriptions, bands, bandsPersonages ->
            val personageCompact = personages.map { personage ->
                PersonageCompact(
                    personageId = personage.id,
                    name = personage.name,
                    surname = getSurname(personage, personageDescriptions),
                    bandName = getBandName(personage, bands, bandsPersonages),
                    personageImage = getPersonageImage(personage, personageDescriptions),
                    bandImage = getBandImage(personage, bands, bandsPersonages),
                    isNewPersonage = getNewInfo(personage, personageDescriptions)
                )
            }
            Response.Success(personageCompact.sortedBy { !it.isNewPersonage })
        }
    }

    private fun getNewInfo(
        personage: Personage,
        personageDescriptions: List<PersonageDescription>
    ): Boolean {
          return  personageDescriptions.findLast { it.personageId == personage.id }?.isNewPersonage ?: false
    }

    private fun getSurname(
        personage: Personage,
        personageDescriptions: List<PersonageDescription>
    ): String? {
        return personageDescriptions
            .filter { it.personageId == personage.id }
            .findLast { it.surname != null }?.surname
    }

    private fun getPersonageImage(
        personage: Personage,
        personageDescriptions: List<PersonageDescription>
    ): String? {
        return personageDescriptions
            .filter { it.personageId == personage.id }
            .findLast { it.image != null }?.image
    }

    private fun getBandName(
        personage: Personage,
        bands: List<Band>,
        bandsPersonages: List<BandPersonage>
    ): String? {
        val personageBand =
            bandsPersonages.lastOrNull { it.personageId == personage.id } ?: return null
        if (personageBand.oldPersonage) return null
        return bands.find { it.id == personageBand.bandId }?.nameBand
    }

    private fun getBandImage(
        personage: Personage,
        bands: List<Band>,
        bandsPersonages: List<BandPersonage>
    ): String? {
        val personageBand =
            bandsPersonages.lastOrNull { it.personageId == personage.id } ?: return null
        if (personageBand.oldPersonage) return null
        return bands.find { it.id == personageBand.bandId }?.image
    }
}