package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Personage
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface PersonageRepository : SafeApiCall {
    suspend fun syncPersonageByPlace(placeId: Int): Response<Unit>
    fun getPersonageList(): Flow<List<Personage>>
    fun getPersonage(personageId: Int): Flow<Personage>
    fun getPersonageByPlace(placeId: Int): Flow<List<Personage>>
    suspend fun getPersonagesById(personagesId: List<Int>): List<Personage>
}