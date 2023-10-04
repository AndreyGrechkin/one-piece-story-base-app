package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.Reward
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

fun PersonageRewardEntity.asDomainModel() = Reward(
    id = id,
    personageId = personageId,
    mangaId = mangaId,
    reward = reward,
    rewardType = rewardType,
    image = image
)
