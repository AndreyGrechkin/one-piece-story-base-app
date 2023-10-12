package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.BandPersonageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BandPersonageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBandPersonage(bandsPersonage: List<BandPersonageEntity>)

    @Query("SELECT * FROM band_personage")
    fun getAllBandPersonage(): Flow<List<BandPersonageEntity>>

    @Query("SELECT * FROM band_personage WHERE personageId =:personageId")
    fun getBandPersonage(personageId: Int): Flow<List<BandPersonageEntity>>

    @Query("SELECT * FROM band_personage WHERE bandId =:bandId")
    fun getBandPersonageByBand(bandId: Int): Flow<List<BandPersonageEntity>>
}