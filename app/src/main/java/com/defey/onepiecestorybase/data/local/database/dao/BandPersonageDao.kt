package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.BandPersonageEntity

@Dao
interface BandPersonageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBandPersonage(bandsPersonage: List<BandPersonageEntity>)
}