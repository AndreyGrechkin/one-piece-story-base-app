package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Personage
import kotlinx.coroutines.flow.Flow

interface PersonageRepository {
    fun getPersonageList(): Flow<List<Personage>>
    fun getPersonage(personageId: Int): Flow<Personage>
}