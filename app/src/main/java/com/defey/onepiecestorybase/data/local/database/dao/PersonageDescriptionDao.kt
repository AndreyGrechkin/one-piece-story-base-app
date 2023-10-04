package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.PersonageDescriptionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonageDescriptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonageDescription(personageDesc: List<PersonageDescriptionEntity>)

    @Query("SELECT * FROM personage_description")
    fun getAllPersonageDescription(): Flow<List<PersonageDescriptionEntity>>

    @Query("SELECT * FROM personage_description WHERE personageId =:personageId")
    fun getPersonageDescription(personageId: Int): Flow<List<PersonageDescriptionEntity>>
}