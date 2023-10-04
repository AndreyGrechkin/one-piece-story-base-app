package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Skill
import kotlinx.coroutines.flow.Flow

interface SkillRepository {

    fun getSkills(personageId: Int): Flow<List<Skill>>
}