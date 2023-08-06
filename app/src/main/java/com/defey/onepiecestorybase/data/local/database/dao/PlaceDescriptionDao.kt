package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.PlaceDescriptionEntity

@Dao
interface PlaceDescriptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlaceDescription(placeDesc: List<PlaceDescriptionEntity>)
}