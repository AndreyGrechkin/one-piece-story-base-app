package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PersonageDescriptionDao
import com.defey.onepiecestorybase.data.local.model.PersonageDescriptionEntity
import com.defey.onepiecestorybase.domain.model.PersonageDescription
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface PersonageDescriptionLocalDataSource {
    fun getAllPersonageDescription(): Flow<List<PersonageDescriptionEntity>>
    fun getPersonageDescription(personageId: Int): Flow<List<PersonageDescriptionEntity>>
    suspend fun getPersonageDescriptionInPlace(personageIdList: List<Int>): List<PersonageDescriptionEntity>
    fun getPersonageDescriptionByFruit(fruitId: Int): Flow<List<PersonageDescriptionEntity>>
}

class PersonageDescriptionLocalDataSourceImpl @Inject constructor(
    private val dao: PersonageDescriptionDao
): PersonageDescriptionLocalDataSource {
    override fun getAllPersonageDescription(): Flow<List<PersonageDescriptionEntity>> {
        return dao.getAllPersonageDescription()
    }

    override fun getPersonageDescription(personageId: Int): Flow<List<PersonageDescriptionEntity>> {
        return dao.getPersonageDescription(personageId)
    }

    override suspend fun getPersonageDescriptionInPlace(personageIdList: List<Int>): List<PersonageDescriptionEntity> {
        return dao.getPersonageDescriptionInPlace(personageIdList)
    }

    override fun getPersonageDescriptionByFruit(fruitId: Int): Flow<List<PersonageDescriptionEntity>> {
        return dao.getPersonageDescriptionByFruit(fruitId).map { descriptionList ->
            dao.getPersonageDescriptionInPlace(descriptionList.map { it.personageId })
        }
    }
}