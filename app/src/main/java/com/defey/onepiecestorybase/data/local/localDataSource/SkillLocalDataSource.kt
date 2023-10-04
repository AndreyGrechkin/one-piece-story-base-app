package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PersonageSkillDao
import com.defey.onepiecestorybase.data.local.model.PersonageSkillEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SkillLocalDataSource {
    fun getSkills(personageId: Int): Flow<List<PersonageSkillEntity>>
}

class SkillLocalDataSourceImpl @Inject constructor(
    private val dao: PersonageSkillDao
) : SkillLocalDataSource {
    override fun getSkills(personageId: Int): Flow<List<PersonageSkillEntity>> {
        return dao.getSkills(personageId)
    }

}