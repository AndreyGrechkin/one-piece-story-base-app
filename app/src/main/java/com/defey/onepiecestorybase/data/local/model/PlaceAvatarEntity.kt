package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.defey.onepiecestorybase.domain.model.AvatarDetailPlace
import com.defey.onepiecestorybase.presentation.utils.Constants.PLACE_AVATAR_TABLE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = PLACE_AVATAR_TABLE)
@TypeConverters(PositionConverter::class)
data class PlaceAvatarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val avatarId: Int,
    val nameAvatar: String,
    val placeId: Int,
    val mangaId: Int,
    val imageAvatar: String,
    val startPosition: List<Pair<Float, Float>>,
    val endPosition: List<Pair<Float, Float>>,
)

fun AvatarDetailPlace.toEntity() = PlaceAvatarEntity(
    id = id,
    avatarId = avatarId,
    nameAvatar = nameAvatar,
    placeId = placeId,
    mangaId = mangaId,
    imageAvatar = imageAvatar,
    startPosition = startPosition,
    endPosition = endPosition
)

fun PlaceAvatarEntity.asDomain() = AvatarDetailPlace(
    id = id,
    avatarId = avatarId,
    nameAvatar = nameAvatar,
    placeId = placeId,
    mangaId = mangaId,
    imageAvatar = imageAvatar,
    startPosition = startPosition,
    endPosition = endPosition
)

class PositionConverter {
    @TypeConverter
    fun fromPositionList(position: List<Pair<Float, Float>>): String {
        val gson = Gson()
        return gson.toJson(position)
    }

    @TypeConverter
    fun toPositionList(positionString: String): List<Pair<Float, Float>> {
        val gson = Gson()
        val listType = object : TypeToken<List<Pair<Float, Float>>>() {}.type
        return gson.fromJson(positionString, listType)
    }
}