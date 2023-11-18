package com.defey.onepiecestorybase.domain.usecase.bond

import com.defey.onepiecestorybase.domain.enum.BondType
import com.defey.onepiecestorybase.domain.model.BondContent
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BondRepository
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import com.defey.onepiecestorybase.presentation.utils.Constants.ALLIANCE
import com.defey.onepiecestorybase.presentation.utils.Constants.ENEMY
import com.defey.onepiecestorybase.presentation.utils.Constants.FAMILY
import com.defey.onepiecestorybase.presentation.utils.Constants.FRIEND
import com.defey.onepiecestorybase.presentation.utils.Constants.FRIENDS
import com.defey.onepiecestorybase.presentation.utils.Constants.NAKAMA
import com.defey.onepiecestorybase.presentation.utils.Constants.TEACHING
import com.defey.onepiecestorybase.presentation.utils.Constants.WORKER
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllBondUseCase(
    private val repo: BondRepository,
    private val personageRepo: PersonageRepository,
    private val personageDescriptionRepo: PersonageDescriptionRepository
) : FlowUseCase<Nothing?, List<BondContent>>() {
    override fun execute(parameters: Nothing?): Flow<Response<List<BondContent>>> {
        return repo.getAllBond().map { bondList ->
            val personageList =
                personageRepo.getPersonagesById(bondList.map { it.personageId }.distinct())
            val personageDescriptionList =
                personageDescriptionRepo.getDescriptionsByPersonageId(personageList.map { it.id }
                    .distinct())
            val bondContent = bondList.map { bond ->
                val image = personageDescriptionList.filter { it.personageId == bond.personageId }
                    .findLast { it.image != null }?.image
                BondContent(
                    id = bond.id,
                    personageId = bond.personageId,
                    mangaId = bond.mangaId,
                    bondPersonageId = bond.bondPersonageId,
                    description = bond.description,
                    bondType = getBondType(bond.bondType),
                    namePersonage = personageList.find { it.id == bond.personageId }?.name,
                    nameBondPersonage = personageList.find { it.id == bond.bondPersonageId }?.name,
                    imagePersonage = image
                )
            }
            Response.Success(bondContent)
        }
    }

    private fun getBondType(type: String?): BondType {
        return when (type) {
            FRIEND, FRIENDS -> BondType.FRIEND
            ENEMY -> BondType.ENEMY
            FAMILY, TEACHING -> BondType.FAMILY
            NAKAMA, WORKER, ALLIANCE -> BondType.NAKAMA
            else -> BondType.NEUTRAL
        }
    }
}