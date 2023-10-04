package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.PersonageWeaponsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonageWeaponsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonageWeapon(weapon: List<PersonageWeaponsEntity>)

    @Query("SELECT * FROM weapon WHERE personageId =:personageId")
    fun getWeapons(personageId: Int): Flow<List<PersonageWeaponsEntity>>
}