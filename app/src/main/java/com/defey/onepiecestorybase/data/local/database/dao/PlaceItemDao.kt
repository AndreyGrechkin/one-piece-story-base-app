package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.PlaceItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlaceItem(placeItem: List<PlaceItemEntity>)

    @Query("SELECT * FROM place_item WHERE placeId=:placeId")
    fun getItemsByPlace(placeId: Int): Flow<List<PlaceItemEntity>>
}