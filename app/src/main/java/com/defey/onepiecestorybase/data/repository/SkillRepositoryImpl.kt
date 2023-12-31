package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.SkillLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.SkillRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Skill
import com.defey.onepiecestorybase.domain.repository.SkillRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SkillRepositoryImpl @Inject constructor(
    private val remote: SkillRemoteDataSource,
    private val local: SkillLocalDataSource,
) : SkillRepository {
    override suspend fun syncSkillByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val skill = remote.getSkillByPlace(placeId).response
            if (skill.isNotEmpty()) {
                local.addSkill(skill.map { it.toEntity() })
            }
        }
    }

    override fun getSkills(personageId: Int): Flow<List<Skill>> {
        return local.getSkills(personageId).map { list -> list.map { it.asDomainModel() } }
    }
}