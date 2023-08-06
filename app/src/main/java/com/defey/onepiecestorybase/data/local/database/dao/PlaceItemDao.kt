package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.PlaceItemEntity

@Dao
interface PlaceItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlaceItem(placeItem: List<PlaceItemEntity>)
}