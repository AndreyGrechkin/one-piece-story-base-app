package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Reward
import kotlinx.coroutines.flow.Flow

interface RewardRepository {
    fun getRewards(personageId: Int): Flow<List<Reward>>
    fun getAllReward(): Flow<List<Reward>>
}