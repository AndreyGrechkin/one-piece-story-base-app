package com.defey.onepiecestorybase.domain.model

data class Bond(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val bondPersonageId: Int,
    val description: String?,
    val bondType: String?
)
