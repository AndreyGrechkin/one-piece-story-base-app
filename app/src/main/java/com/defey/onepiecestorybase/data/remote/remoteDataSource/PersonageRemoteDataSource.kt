package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.PersonageApiResponse
import javax.inject.Inject

interface PersonageRemoteDataSource {
    suspend fun getPersonageByPlace(placeId: Int): PersonageApiResponse
}

class PersonageRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : PersonageRemoteDataSource {
    override suspend fun getPersonageByPlace(placeId: Int): PersonageApiResponse {
        return api.getPersonageByPlace(placeId)
    }

}