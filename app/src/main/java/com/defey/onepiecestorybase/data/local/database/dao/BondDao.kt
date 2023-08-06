package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.BondEntity

@Dao
interface BondDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBond(bonds: List<BondEntity>)
}