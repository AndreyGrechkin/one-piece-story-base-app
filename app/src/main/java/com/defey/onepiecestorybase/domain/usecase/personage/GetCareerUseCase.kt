package com.defey.onepiecestorybase.domain.usecase.personage

import com.defey.onepiecestorybase.domain.model.BandPersonage
import com.defey.onepiecestorybase.domain.model.Career
import com.defey.onepiecestorybase.domain.model.PersonageDescription
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BandPersonageRepository
import com.defey.onepiecestorybase.domain.repository.BandRepository
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetCareerUseCase(
    private val repo: PersonageDescriptionRepository,
    private val bandRepo: BandRepository,
    private val bandPersonageRepo: BandPersonageRepository
) : FlowUseCase<Int?, List<Career>>() {
    override fun execute(parameters: Int?): Flow<Response<List<Career>>> {
        val param = parameters ?: throw NullPointerException("personageId can't be null")
        return repo.getPersonageDescription(param)
            .combine(bandPersonageRepo.getBandPersonage(param)) { personageDescriptionList, bandPersonageList ->
                val careerList = mergeLists(personageDescriptionList, bandPersonageList)
                Response.Success(careerList)
            }

    }

    private suspend fun mergeLists(
        personageList: List<PersonageDescription>,
        bandPersonageList: List<BandPersonage>
    ): List<Career> {
        val careerList = mutableListOf<Career>()
        personageList.map { one ->
            if (one.personageType == null) return@map
            val second = bandPersonageList.find { it.mangaId == one.mangaId }

            if (second != null) {
                careerList.addAll(filteredCareer(bandPersonageList, one, careerList))
            } else {
                val career = Career(one.mangaId, one.personageType, one.career, null, null, null)
                careerList.add(career)
            }
        }
        return careerList.reversed()
    }

    private suspend fun filteredCareer(
        bandPersonageList: List<BandPersonage>,
        one: PersonageDescription,
        careers: List<Career>
    ): List<Career> {
        val careerList = mutableListOf<Career>()
        var bandType: String? = null
        val bandPersonage = bandPersonageList
            .groupBy { it.bandId }
            .mapValues { (_, values) -> values.maxByOrNull { it.id } }
            .values
            .filterNotNull()
        bandPersonage.forEach { personageBand ->
            val band = bandRepo.getBand(personageBand.bandId)
            if (careers.map { it.bandName }.contains(band?.nameBand)) return@forEach
            if (bandType != null && bandType != band?.bandType) return@forEach
            bandType = band?.bandType
            val career =
                Career(
                    one.mangaId,
                    one.personageType,
                    personageBand.career,
                    band?.nameBand,
                    band?.image,
                    bandPersonage.find { it.bandId == band?.id }?.oldPersonage
                )
            careerList.add(career)
        }
        return careerList
    }
}