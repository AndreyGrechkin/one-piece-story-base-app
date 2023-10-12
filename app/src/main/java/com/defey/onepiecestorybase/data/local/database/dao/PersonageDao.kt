package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.PersonageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonage(personage: List<PersonageEntity>)

    @Query("SELECT * FROM personage")
    fun getAllPersonage(): Flow<List<PersonageEntity>>

    @Query("SELECT * FROM personage WHERE id =:personageId")
    fun getPersonage(personageId: Int): Flow<PersonageEntity>

    @Query("SELECT * FROM personage WHERE placeId=:placeId")
    fun getPersonageByPlace(placeId: Int): Flow<List<PersonageEntity>>

    @Query("SELECT * FROM personage WHERE id IN(:personagesId)")
    suspend fun getPersonageInBand(personagesId: List<Int>): List<PersonageEntity>
}