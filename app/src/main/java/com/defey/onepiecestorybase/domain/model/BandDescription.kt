package com.defey.onepiecestorybase.domain.model

data class BandDescription(
    val id: Int,
    val bandId: Int,
    val mangaId: Int,
    val description: String?,
    val isNewBand: Boolean
)
