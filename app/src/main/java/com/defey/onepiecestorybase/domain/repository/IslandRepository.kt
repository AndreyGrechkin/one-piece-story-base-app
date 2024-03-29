package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Island
import com.defey.onepiecestorybase.domain.model.IslandTransit
import com.defey.onepiecestorybase.domain.model.PersonageIsland
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface IslandRepository : SafeApiCall {
    suspend fun synchronizeIsland(): Response<Unit>
    fun getIsland(): Flow<List<Island>>
    suspend fun synchronizePersonageIsland(placeId: Int): Response<Unit>
    suspend fun synchronizeIslandTransit(placeId: Int): Response<Unit>
    fun getPersonageIsland(): Flow<List<PersonageIsland>>
    fun getIslandTransit(): Flow<List<IslandTransit>>
    suspend fun deleteOldAvatarPlace(lastPlaceId: Int, avatarName: String)
}