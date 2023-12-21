package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import com.defey.onepiecestorybase.domain.model.Skill
import kotlinx.coroutines.flow.Flow

interface SkillRepository : SafeApiCall {
    suspend fun syncSkillByPlace(placeId: Int): Response<Unit>
    fun getSkills(personageId: Int): Flow<List<Skill>>
}