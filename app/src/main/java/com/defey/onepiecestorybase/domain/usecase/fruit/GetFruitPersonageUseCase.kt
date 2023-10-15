package com.defey.onepiecestorybase.domain.usecase.fruit

import com.defey.onepiecestorybase.domain.model.FruitPersonage
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFruitPersonageUseCase(
    private val repo: PersonageDescriptionRepository,
    private val personageRepo: PersonageRepository
) : FlowUseCase<Int?, List<FruitPersonage>>() {
    override fun execute(parameters: Int?): Flow<Response<List<FruitPersonage>>> {
        val param = parameters ?: throw NullPointerException("fruitId can't be null")
        return repo.getPersonageDescriptionByFruit(param).map { listDescription ->
            val personageList =
                personageRepo.getPersonageInBand(listDescription.map { it.personageId }.distinct())
            val fruitPersonageList = personageList.map { personage ->
                val image = listDescription.filter { it.personageId == personage.id }
                    .findLast { it.image != null }?.image
                FruitPersonage(
                    personageId = personage.id,
                    personageName = personage.name,
                    personageImage = image,
                    fruitId = param
                )
            }
            Response.Success(fruitPersonageList)
        }
    }
}