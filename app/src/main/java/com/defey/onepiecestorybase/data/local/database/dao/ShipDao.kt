package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.ShipEntity

@Dao
interface ShipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShip(ship: List<ShipEntity>)
}