package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.RewardLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.RewardRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Reward
import com.defey.onepiecestorybase.domain.repository.RewardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RewardRepositoryImpl @Inject constructor(
    private val remote: RewardRemoteDataSource,
    private val local: RewardLocalDataSource
) : RewardRepository {
    override fun getRewards(personageId: Int): Flow<List<Reward>> {
        return local.getRewards(personageId).map { list -> list.map { it.asDomainModel() } }
    }
}