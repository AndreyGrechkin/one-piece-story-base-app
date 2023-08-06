package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.MangaEntity

@Dao
interface MangaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addManga(manga: List<MangaEntity>)

    @Query("SELECT * FROM manga")
    suspend fun getManga(): List<MangaEntity>
}