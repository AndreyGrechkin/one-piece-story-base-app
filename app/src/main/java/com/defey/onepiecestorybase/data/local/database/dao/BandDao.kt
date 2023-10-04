package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.BandEntity
import com.defey.onepiecestorybase.domain.model.Band
import kotlinx.coroutines.flow.Flow

@Dao
interface BandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBand(bands: List<BandEntity>)

    @Query("SELECT * FROM band")
    fun getAllBand(): Flow<List<BandEntity>>

    @Query("SELECT * FROM band WHERE id IN (:bandsId)")
    fun getBands(bandsId: List<Int>): Flow<List<BandEntity>>

    @Query("SELECT * FROM band WHERE id =:bandId")
    suspend fun getBand(bandId: Int): BandEntity?

    @Query("SELECT * FROM band WHERE id IN (:bandsId)")
    suspend fun getBandList(bandsId: List<Int>): List<BandEntity>
}