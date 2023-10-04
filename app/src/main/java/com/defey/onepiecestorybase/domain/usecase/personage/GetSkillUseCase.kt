package com.defey.onepiecestorybase.domain.usecase.personage

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Skill
import com.defey.onepiecestorybase.domain.repository.SkillRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSkillUseCase(
    private val repo: SkillRepository
) : FlowUseCase<Int, List<Skill>>() {
    override fun execute(parameters: Int?): Flow<Response<List<Skill>>> {
        val param = parameters ?: throw NullPointerException("personageId can't be null")
        return repo.getSkills(param).map { value ->
            Response.Success(value)
        }
    }
}