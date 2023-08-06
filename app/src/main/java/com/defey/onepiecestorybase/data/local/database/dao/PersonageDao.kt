package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.PersonageEntity

@Dao
interface PersonageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonage(personage: List<PersonageEntity>)
}