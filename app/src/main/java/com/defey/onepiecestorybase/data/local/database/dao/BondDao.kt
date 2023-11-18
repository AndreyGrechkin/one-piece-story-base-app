package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.BondEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BondDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBond(bonds: List<BondEntity>)

    @Query("SELECT * FROM bond")
    fun getAllBond(): Flow<List<BondEntity>>
}