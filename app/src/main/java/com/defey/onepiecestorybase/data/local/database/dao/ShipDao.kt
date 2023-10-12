package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.ShipEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShip(ship: List<ShipEntity>)

    @Query("SELECT * FROM ship WHERE bandId=:bandId")
    fun getShipByBand(bandId: Int): Flow<List<ShipEntity>>
}