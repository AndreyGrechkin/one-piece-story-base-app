package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.PersonageDescription
import kotlinx.coroutines.flow.Flow

interface PersonageDescriptionRepository {
    fun getAllPersonageDescription(): Flow<List<PersonageDescription>>
    fun getPersonageDescription(personageId: Int): Flow<List<PersonageDescription>>
    suspend fun getDescriptionsByPersonageId(personageIdList: List<Int>): List<PersonageDescription>
    fun getPersonageDescriptionByFruit(fruitId: Int): Flow<List<PersonageDescription>>
    suspend fun sendReadPersonage(personageId: Int)
}