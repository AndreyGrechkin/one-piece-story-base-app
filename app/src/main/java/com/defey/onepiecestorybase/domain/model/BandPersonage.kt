package com.defey.onepiecestorybase.domain.model

data class BandPersonage(
    val id: Int,
    val personageId: Int,
    val bandId: Int,
    val mangaId: Int,
    val career: String?,
    val oldPersonage: Boolean
)
