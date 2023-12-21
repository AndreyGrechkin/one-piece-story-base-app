package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import com.defey.onepiecestorybase.domain.model.Weapons
import kotlinx.coroutines.flow.Flow

interface WeaponsRepository : SafeApiCall {
    suspend fun syncWeaponByPlace(placeId: Int): Response<Unit>
    fun getWeapons(personageId: Int): Flow<List<Weapons>>
}