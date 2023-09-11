package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.IslandLocalDS
import com.defey.onepiecestorybase.data.local.model.IslandEntity
import com.defey.onepiecestorybase.data.local.model.asDomain
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.IslandRemoteDS
import com.defey.onepiecestorybase.domain.model.Island
import com.defey.onepiecestorybase.domain.model.IslandPlace
import com.defey.onepiecestorybase.domain.model.IslandTransit
import com.defey.onepiecestorybase.domain.model.PersonageIsland
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.IslandRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IslandRepositoryImpl @Inject constructor(
    private val remote: IslandRemoteDS,
    private val local: IslandLocalDS
) : IslandRepository {
    override suspend fun synchronizeIsland(): Response<Unit> {
        return safeApiCall {
            val response = remote.getIsland().response
            local.insertIsland(response.map { it.toEntity() })
        }
    }

    override fun getIsland(): Flow<List<Island>> {
        return local.getIsland()
            .map { value: List<IslandEntity> -> value.map { it.asDomain() } }
    }

    override suspend fun synchronizePersonageIsland(placeId: Int): Response<Unit> {
        return safeApiCall {
            val personageIsland = remote.getPersonageIsland(placeId)
            val islandTransit = remote.getIslandTransit(placeId)
            local.insertPersonageIsland(personageIsland.map { it.toEntity() })
            local.insertIslandTransit(islandTransit.map { it.toEntity() })
        }
    }

    override fun getPersonageIsland(placeId: Int): Flow<List<PersonageIsland>> {
        return local.getPersonageIsland(placeId)
            .map { value -> value.map { it.asDomain() } }
    }

    override fun getIslandTransit(placeId: Int): Flow<List<IslandTransit>> {
        return local.getIslandTransit(placeId)
            .map { value -> value.map { it.asDomain() } }
    }

    override suspend fun deleteOldAvatarPlace(lastPlaceId: Int, avatarName: String) {
        local.deleteLocalOldAvatarPlace(lastPlaceId, avatarName)
    }
}