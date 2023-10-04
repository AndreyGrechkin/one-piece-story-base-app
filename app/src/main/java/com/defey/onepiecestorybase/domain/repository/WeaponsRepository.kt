package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Weapons
import kotlinx.coroutines.flow.Flow

interface WeaponsRepository {

    fun getWeapons(personageId: Int): Flow<List<Weapons>>
}