package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PersonageDao
import com.defey.onepiecestorybase.data.local.model.PersonageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PersonageLocalDataSource {
    fun getAllPersonage(): Flow<List<PersonageEntity>>
    fun getPersonage(personageId: Int): Flow<PersonageEntity>
}

class PersonageLocalDataSourceImpl @Inject constructor(
    private val dao: PersonageDao
) : PersonageLocalDataSource {
    override fun getAllPersonage(): Flow<List<PersonageEntity>> {
        return dao.getAllPersonage()
    }

    override fun getPersonage(personageId: Int): Flow<PersonageEntity> {
        return dao.getPersonage(personageId)
    }

}