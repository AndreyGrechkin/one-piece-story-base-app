package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.Reward
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface RewardRepository : SafeApiCall {
    suspend fun syncRewardByPlace(placeId: Int): Response<Unit>
    fun getRewards(personageId: Int): Flow<List<Reward>>
    fun getAllReward(): Flow<List<Reward>>
}