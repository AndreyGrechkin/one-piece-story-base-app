package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.PersonageRewardEntity

@Dao
interface PersonageRewardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonageReward(reward: List<PersonageRewardEntity>)
}