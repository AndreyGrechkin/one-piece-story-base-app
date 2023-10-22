package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.FruitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FruitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFruit(fruit: List<FruitEntity>)

    @Query("SELECT * FROM fruit")
    fun getAllFruit(): Flow<List<FruitEntity>>

    @Query("SELECT * FROM fruit WHERE id =:fruitId")
    fun getFruit(fruitId: Int): Flow<FruitEntity?>

    @Query("UPDATE fruit SET isNewFruit = 0 WHERE id=:fruitId")
    suspend fun sendReadFruit(fruitId: Int)
}