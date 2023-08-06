package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.presentation.utils.Constants.REWARD_TABLE

@Entity(tableName = REWARD_TABLE)
data class PersonageRewardEntity(
    @PrimaryKey
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val reward: Int,
    val rewardType: String,
    val image: String?,
)
