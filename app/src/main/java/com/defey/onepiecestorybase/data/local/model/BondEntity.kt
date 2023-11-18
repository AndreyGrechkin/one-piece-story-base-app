package com.defey.onepiecestorybase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.defey.onepiecestorybase.domain.model.Bond
import com.defey.onepiecestorybase.presentation.utils.Constants.BOND_TABLE

@Entity(tableName = BOND_TABLE)
data class BondEntity(
    @PrimaryKey
    val id: Int,
    val personageId: Int,
    val mangaId: Int,
    val bondPersonageId: Int,
    val description: String?,
    val bondType: String?
)

fun BondEntity.asDomainModel() = Bond(
    id = id,
    personageId = personageId,
    mangaId = mangaId,
    bondPersonageId = bondPersonageId,
    description = description,
    bondType = bondType
)