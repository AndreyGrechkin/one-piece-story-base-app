package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.PlaceTransitItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceTransitItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlaceTransit(placeTransit: List<PlaceTransitItemEntity>)

    @Query("SELECT * FROM place_transit WHERE mangaId=:mangaId")
    fun getItemTransitByManga(mangaId: Int): Flow<List<PlaceTransitItemEntity>>
}