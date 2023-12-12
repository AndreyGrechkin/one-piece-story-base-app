package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.defey.onepiecestorybase.data.local.model.PlaceAvatarEntity


@Dao
interface PlaceAvatarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlaceAvatar(placeAvatar: List<PlaceAvatarEntity>)

    @Update
    suspend fun updatePlaceAvatar(placeAvatar: PlaceAvatarEntity)

    @Query("SELECT * FROM place_avatar WHERE placeId=:placeId AND avatarId=:avatarId")
    suspend fun getPlaceAvatarById(placeId: Int, avatarId: Int): PlaceAvatarEntity?

    @Query("SELECT * FROM place_avatar WHERE placeId=:placeId")
    suspend fun getPlaceAvatar(placeId: Int): List<PlaceAvatarEntity>
}