package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PersonageDescriptionDao
import com.defey.onepiecestorybase.data.local.model.PersonageDescriptionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface PersonageDescriptionLocalDataSource {
    suspend fun addPersonageDescription(personageDescriptions: List<PersonageDescriptionEntity>)
    fun getAllPersonageDescription(): Flow<List<PersonageDescriptionEntity>>
    fun getPersonageDescription(personageId: Int): Flow<List<PersonageDescriptionEntity>>
    suspend fun getDescriptionsByPersonageId(personageIdList: List<Int>): List<PersonageDescriptionEntity>
    fun getPersonageDescriptionByFruit(fruitId: Int): Flow<List<PersonageDescriptionEntity>>
    suspend fun sendReadPersonage(personageId: Int)
}

class PersonageDescriptionLocalDataSourceImpl @Inject constructor(
    private val dao: PersonageDescriptionDao
) : PersonageDescriptionLocalDataSource {
    override suspend fun addPersonageDescription(personageDescriptions: List<PersonageDescriptionEntity>) {
        dao.addPersonageDescription(personageDescriptions)
    }

    override fun getAllPersonageDescription(): Flow<List<PersonageDescriptionEntity>> {
        return dao.getAllPersonageDescription()
    }

    override fun getPersonageDescription(personageId: Int): Flow<List<PersonageDescriptionEntity>> {
        return dao.getPersonageDescription(personageId)
    }

    override suspend fun getDescriptionsByPersonageId(personageIdList: List<Int>): List<PersonageDescriptionEntity> {
        return dao.getDescriptionsByPersonageId(personageIdList)
    }

    override fun getPersonageDescriptionByFruit(fruitId: Int): Flow<List<PersonageDescriptionEntity>> {
        return dao.getPersonageDescriptionByFruit(fruitId).map { descriptionList ->
            dao.getDescriptionsByPersonageId(descriptionList.map { it.personageId })
        }
    }

    override suspend fun sendReadPersonage(personageId: Int) {
        dao.sendReadPersonage(personageId)
    }
}