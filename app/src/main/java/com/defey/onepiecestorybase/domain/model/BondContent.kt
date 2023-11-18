package com.defey.onepiecestorybase.domain.model

import com.defey.onepiecestorybase.domain.enum.BondType

data class BondContent(
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val bondPersonageId: Int,
    val description: String?,
    val bondType: BondType,
    val namePersonage: String?,
    val nameBondPersonage: String?,
    val imagePersonage: String?
)
