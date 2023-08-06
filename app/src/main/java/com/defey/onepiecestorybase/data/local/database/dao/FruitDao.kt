package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.FruitEntity

@Dao
interface FruitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFruit(fruit: List<FruitEntity>)
}