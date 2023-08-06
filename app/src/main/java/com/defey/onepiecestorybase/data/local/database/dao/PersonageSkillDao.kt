package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.PersonageSkillEntity

@Dao
interface PersonageSkillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonageSkill(skill: List<PersonageSkillEntity>)
}