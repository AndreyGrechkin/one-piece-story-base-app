package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.InventoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInventory(inventory: List<InventoryEntity>)

    @Query("SELECT * FROM inventory")
    fun getAllSubject(): Flow<List<InventoryEntity>>

    @Query("SELECT * FROM inventory WHERE placeId=:placeId")
    fun getSubjectInPlace(placeId: Int): Flow<List<InventoryEntity>>

    @Query("SELECT * FROM inventory WHERE Id=:subjectId")
    fun getSubject(subjectId: Int): Flow<InventoryEntity?>

    @Query("UPDATE inventory SET isNewSubject = 0 WHERE id=:subjectId")
    suspend fun sendReadSubject(subjectId: Int)
}