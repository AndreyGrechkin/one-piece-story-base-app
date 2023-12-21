package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.Subject
import com.defey.onepiecestorybase.presentation.utils.Constants.SUBJECT_TABLE

@Entity(tableName = SUBJECT_TABLE)
data class SubjectEntity(
    @PrimaryKey
    val id: Int,
    val mangaId: Int,
    val placeId: Int,
    val image: String?,
    val description: String?,
    val name: String,
    val nameJp: String?,
    val transcriptionJp: String?,
    val isNewSubject: Boolean
)

fun SubjectEntity.asDomainModel() = Subject(
    id = id,
    mangaId = mangaId,
    placeId = placeId,
    image = image,
    description = description,
    name = name,
    nameJp = nameJp,
    transcriptionJp = transcriptionJp,
    isNewSubject = isNewSubject
)
