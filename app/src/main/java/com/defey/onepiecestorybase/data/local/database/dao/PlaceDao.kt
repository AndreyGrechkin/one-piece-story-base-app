package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.PlaceEntity
import com.defey.onepiecestorybase.domain.model.Place
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlace(place: PlaceEntity)

    @Query("SELECT * FROM place")
    suspend fun getPlace(): PlaceEntity

    @Query("SELECT * FROM place WHERE id = (SELECT MAX(id) FROM place)")
    fun getLastPlace(): Flow<PlaceEntity?>

    @Query("SELECT * FROM place")
    fun getAllPlaceFlow(): Flow<List<PlaceEntity>>
}