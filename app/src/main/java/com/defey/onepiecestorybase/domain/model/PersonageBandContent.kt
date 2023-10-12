package com.defey.onepiecestorybase.domain.model

data class PersonageBandContent(
    val personageId: Int,
    val personageName: String,
    val personageImage: String?,
    val personageIsFruiting: Boolean,
    val career: String?,
    val oldPersonage: Boolean
)
