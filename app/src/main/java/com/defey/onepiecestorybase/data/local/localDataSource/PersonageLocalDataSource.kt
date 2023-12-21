package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PersonageDao
import com.defey.onepiecestorybase.data.local.model.PersonageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PersonageLocalDataSource {
    suspend fun addPersonage(personages: List<PersonageEntity>)
    fun getAllPersonage(): Flow<List<PersonageEntity>>
    fun getPersonage(personageId: Int): Flow<PersonageEntity>
    fun getPersonageByPlace(placeId: Int): Flow<List<PersonageEntity>>
    suspend fun getPersonagesById(personagesId: List<Int>): List<PersonageEntity>

}

class PersonageLocalDataSourceImpl @Inject constructor(
    private val dao: PersonageDao
) : PersonageLocalDataSource {
    override suspend fun addPersonage(personages: List<PersonageEntity>) {
        dao.addPersonage(personages)
    }

    override fun getAllPersonage(): Flow<List<PersonageEntity>> {
        return dao.getAllPersonage()
    }

    override fun getPersonage(personageId: Int): Flow<PersonageEntity> {
        return dao.getPersonage(personageId)
    }

    override fun getPersonageByPlace(placeId: Int): Flow<List<PersonageEntity>> {
        return dao.getPersonageByPlace(placeId)
    }

    override suspend fun getPersonagesById(personagesId: List<Int>): List<PersonageEntity> {
        return dao.getPersonagesById(personagesId)
    }

}