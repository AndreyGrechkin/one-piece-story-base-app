package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.PersonageRewardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonageRewardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonageReward(reward: List<PersonageRewardEntity>)

    @Query("SELECT * FROM reward WHERE personageId =:personageId")
    fun getRewards(personageId: Int): Flow<List<PersonageRewardEntity>>
}