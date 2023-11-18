package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Personage
import kotlinx.coroutines.flow.Flow

interface PersonageRepository {
    fun getPersonageList(): Flow<List<Personage>>
    fun getPersonage(personageId: Int): Flow<Personage>
    fun getPersonageByPlace(placeId: Int): Flow<List<Personage>>
    suspend fun getPersonagesById(personagesId: List<Int>): List<Personage>
}