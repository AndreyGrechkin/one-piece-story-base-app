package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.IslandEntity
import com.defey.onepiecestorybase.data.local.model.IslandTransitEntity
import com.defey.onepiecestorybase.data.local.model.PersonageIslandEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IslandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIsland(island: List<IslandEntity>)

    @Query("SELECT * FROM island")
    fun getIsland(): Flow<List<IslandEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonageIsland(personageIsland: List<PersonageIslandEntity>)

    @Query("SELECT * FROM personage_island")
    fun getPersonageIsland(): Flow<List<PersonageIslandEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIslandTransit(islandTransit: List<IslandTransitEntity>)

    @Query("SELECT * FROM personage_island_transit")
    fun getIslandTransit(): Flow<List<IslandTransitEntity>>

    @Query("DELETE FROM personage_island WHERE placeId=:lastPlaceId AND nameAvatar=:nameAvatar")
    suspend fun deleteOldAvatar(lastPlaceId: Int, nameAvatar: String)
}