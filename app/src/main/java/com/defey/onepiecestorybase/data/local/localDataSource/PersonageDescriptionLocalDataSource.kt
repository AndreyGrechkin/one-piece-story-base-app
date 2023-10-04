package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PersonageDescriptionDao
import com.defey.onepiecestorybase.data.local.model.PersonageDescriptionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PersonageDescriptionLocalDataSource {
    fun getAllPersonageDescription(): Flow<List<PersonageDescriptionEntity>>
    fun getPersonageDescription(personageId: Int): Flow<List<PersonageDescriptionEntity>>
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

}