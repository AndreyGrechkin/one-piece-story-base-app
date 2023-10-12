package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.BandDescriptionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BandDescriptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBandDescription(bandDesc: List<BandDescriptionEntity>)

    @Query("SELECT * FROM band_description WHERE bandId=:bandId")
    fun getBandDescription(bandId: Int): Flow<List<BandDescriptionEntity>>
}