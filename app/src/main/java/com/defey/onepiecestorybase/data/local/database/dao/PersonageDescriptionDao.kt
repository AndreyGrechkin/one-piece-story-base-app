package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.PersonageDescriptionEntity

@Dao
interface PersonageDescriptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonageDescription(personageDesc: List<PersonageDescriptionEntity>)
}