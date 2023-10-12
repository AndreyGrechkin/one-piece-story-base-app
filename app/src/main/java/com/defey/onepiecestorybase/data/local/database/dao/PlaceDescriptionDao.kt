package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.PlaceDescriptionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDescriptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlaceDescription(placeDesc: List<PlaceDescriptionEntity>)

    @Query("SELECT * FROM place_description")
    fun getAllDescription(): Flow<List<PlaceDescriptionEntity>>

    @Query("SELECT * FROM place_description WHERE placeId=:placeId")
    fun getLocationDescription(placeId: Int): Flow<List<PlaceDescriptionEntity>>
}