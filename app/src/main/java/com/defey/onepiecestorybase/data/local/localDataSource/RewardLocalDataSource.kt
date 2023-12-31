package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PersonageRewardDao
import com.defey.onepiecestorybase.data.local.model.PersonageRewardEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RewardLocalDataSource {
    suspend fun addReward(rewards: List<PersonageRewardEntity>)
    fun getRewards(personageId: Int): Flow<List<PersonageRewardEntity>>
    fun getAllReward(): Flow<List<PersonageRewardEntity>>
}

class RewardLocalDataSourceImpl @Inject constructor(
    private val dao: PersonageRewardDao
) : RewardLocalDataSource {
    override suspend fun addReward(rewards: List<PersonageRewardEntity>) {
        dao.addPersonageReward(rewards)
    }

    override fun getRewards(personageId: Int): Flow<List<PersonageRewardEntity>> {
        return dao.getRewards(personageId)
    }

    override fun getAllReward(): Flow<List<PersonageRewardEntity>> {
        return dao.getAllRewards()
    }

}
