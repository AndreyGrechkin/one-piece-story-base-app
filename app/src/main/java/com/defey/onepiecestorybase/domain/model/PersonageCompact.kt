package com.defey.onepiecestorybase.domain.model

data class PersonageCompact(
    val personageId: Int,
    val name: String,
    val surname: String?,
    val bandName: String?,
    val personageImage: String?,
    val bandImage: String?,
    val isNewPersonage: Boolean
)
