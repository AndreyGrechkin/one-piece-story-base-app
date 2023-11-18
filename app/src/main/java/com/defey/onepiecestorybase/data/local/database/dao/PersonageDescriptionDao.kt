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

    @Query("SELECT * FROM personage_description WHERE personageId IN(:personageId)")
    suspend fun getDescriptionsByPersonageId(personageId: List<Int>): List<PersonageDescriptionEntity>

    @Query("SELECT * FROM personage_description WHERE fruitId =:fruitId")
    fun getPersonageDescriptionByFruit(fruitId: Int): Flow<List<PersonageDescriptionEntity>>

    @Query("UPDATE personage_description SET isNewPersonage = 0 WHERE personageId=:personageId")
    suspend fun sendReadPersonage(personageId: Int)

    @Query("UPDATE personage_description SET isNewPersonage = 1 WHERE personageId IN(:personagesId)")
    suspend fun updateNewPersonage(personagesId: List<Int>)
}