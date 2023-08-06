package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.PlaceTransitItemEntity

@Dao
interface PlaceTransitItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlaceTransit(placeTransit: List<PlaceTransitItemEntity>)
}