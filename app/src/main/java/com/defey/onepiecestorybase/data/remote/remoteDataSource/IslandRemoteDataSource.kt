package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.IslandApiResponse
import com.defey.onepiecestorybase.data.remote.model.IslandTransitResponseBody
import com.defey.onepiecestorybase.data.remote.model.PersonageIslandResponseBody
import javax.inject.Inject

interface IslandRemoteDataSource {
    suspend fun getIsland(): IslandApiResponse
    suspend fun getPersonageIsland(placeId: Int): List<PersonageIslandResponseBody>
    suspend fun getIslandTransit(placeId: Int): List<IslandTransitResponseBody>
}

class IslandRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : IslandRemoteDataSource {
    override suspend fun getIsland(): IslandApiResponse {
        return api.getIsland()
    }

    override suspend fun getPersonageIsland(placeId: Int): List<PersonageIslandResponseBody> {
        return api.getPersonageIsland(placeId).response
    }

    override suspend fun getIslandTransit(placeId: Int): List<IslandTransitResponseBody> {
        return api.getIslandTransit(placeId).response
    }
}