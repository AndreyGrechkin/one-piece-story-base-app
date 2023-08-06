package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.PersonageWeaponsEntity

@Dao
interface PersonageWeaponsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonageWeapon(weapon: List<PersonageWeaponsEntity>)
}