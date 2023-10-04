package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.Skill
import com.defey.onepiecestorybase.presentation.utils.Constants.SKILL_TABLE

@Entity(tableName = SKILL_TABLE)
data class PersonageSkillEntity(
    @PrimaryKey
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val nameSkill: String,
    val description: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
)

fun PersonageSkillEntity.asDomainModel() = Skill(
    id = id,
    personageId = personageId,
    mangaId = mangaId,
    nameSkill = nameSkill,
    description = description,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp
)
