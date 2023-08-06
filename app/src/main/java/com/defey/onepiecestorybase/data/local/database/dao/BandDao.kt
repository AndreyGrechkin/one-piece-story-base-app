package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.BandEntity

@Dao
interface BandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBand(bands: List<BandEntity>)
}