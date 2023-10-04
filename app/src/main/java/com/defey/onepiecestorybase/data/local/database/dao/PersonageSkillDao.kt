package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.PersonageSkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonageSkillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonageSkill(skill: List<PersonageSkillEntity>)

    @Query("SELECT * FROM skill WHERE personageId =:personageId")
    fun getSkills(personageId: Int): Flow<List<PersonageSkillEntity>>
}