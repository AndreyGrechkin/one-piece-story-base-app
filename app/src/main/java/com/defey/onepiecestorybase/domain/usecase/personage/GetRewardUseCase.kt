package com.defey.onepiecestorybase.domain.usecase.personage

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Reward
import com.defey.onepiecestorybase.domain.repository.RewardRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRewardUseCase(
    private val repo: RewardRepository
) : FlowUseCase<Int?, List<Reward>>() {
    override fun execute(parameters: Int?): Flow<Response<List<Reward>>> {
        val param = parameters ?: throw NullPointerException("personageId can't be null")
        return repo.getRewards(param).map { value ->
            Response.Success(value.reversed())
        }
    }
}