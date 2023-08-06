package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.defey.onepiecestorybase.data.local.model.BandDescriptionEntity

@Dao
interface BandDescriptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBandDescription(bandDesc: List<BandDescriptionEntity>)
}