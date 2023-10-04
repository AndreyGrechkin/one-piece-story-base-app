package com.defey.onepiecestorybase.domain.model

data class Band(
    val id: Int,
    val mangaId: Int,
    val nameBand: String,
    val image: String?,
    val bandType: String?,
    val nameJp: String?,
    val transcriptionJp: String?,
)

fun Band.toBandCompact() = BandCompact(
    bandId = id,
    bandName = nameBand,
    bandType = bandType,
    bandImage = image
)
